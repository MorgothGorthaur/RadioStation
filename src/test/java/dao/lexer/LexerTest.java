package dao.lexer;

import exception.*;
import org.junit.jupiter.api.Test;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.experience.Experience;
import personality.experience.WorkOnRadioExperienceImpl;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Music;
import translation.part.Part;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LexerTest {

    Lexer lexer = new LexerImpl();
    @Test
    void testInterpret_shouldReturnRadioBroadcaster() {
        var inputString = "R, Victor Tarasov, (E, some radio, 5.0=>E, another radio, 6.0=>), T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        var expected = new RadioBroadcaster("Victor Tarasov",
                new LinkedHashSet<>(List.of(new WorkOnRadioExperienceImpl("some radio", 5),
                        new WorkOnRadioExperienceImpl("another radio", 6))),
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<>(
                        List.of(new Music("some singer","some music", 5.0), new Advertisement("some product",5.0),
                                new Music("another singer", "another music", 5.0)))))));
        assertThat(lexer.interpret(inputString)).isEqualTo(expected);
    }
    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withEmptyExperience() {
        var inputString = "R, Victor Tarasov, (), T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        var expected = new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(), new LinkedHashSet<Translation>(List.of(
                new TranslationImpl(10.0, 15.0, new ArrayList<>(
                        List.of(new Music("some singer", "some music", 5.0),
                                new Advertisement("some product", 5.0),
                new Music("another singer", "another music", 5.0)))))));
        assertThat(lexer.interpret(inputString)).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withEmptyTranslation() {
        var inputString = "R, Victor Tarasov, (E, some radio, 5.0=>E, another radio, 6.0=>), ";
        var expected = new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(
                List.of(new WorkOnRadioExperienceImpl("some radio", 5.0),
                        new WorkOnRadioExperienceImpl("another radio", 6.0))));
        assertThat(lexer.interpret(inputString)).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withTranslation_withoutAnyParts() {
        var inputString = "R, Victor Tarasov, (E, some radio, 5.0=>E, another radio, 6.0=>), T 10.0 15.0 []|";
        var expected = new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(
                List.of(new WorkOnRadioExperienceImpl("some radio", 5.0),
                        new WorkOnRadioExperienceImpl("another radio", 6.0))),
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<Part>()))));
        assertThat(lexer.interpret(inputString)).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldReturnGuestBroadcaster() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [M, some singer, some music, 5.0=>]|";
        var expected = new GuestBroadcaster("Victor Tarasov", "some resume, @ gg.",
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<>(List.of(new Music("some singer", "some music", 5.0)))))));
        assertThat(lexer.interpret(inputString)).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldThrowCantReadAdvertisingException() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product=>M, another singer, another music, 5.0=>]|";
        assertThatThrownBy(() -> lexer.interpret(inputString)).isInstanceOf(CantReadAdvertisingException.class);
    }

    @Test
    void testInterpret_shouldThrowCantReadMusicException() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [M, some singer, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThatThrownBy(() -> lexer.interpret(inputString)).isInstanceOf(CantReadMusicException.class);
    }

    @Test
    void testInterpret_shouldThrowCantReadInterviewException() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [I, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThatThrownBy(() -> lexer.interpret(inputString)).isInstanceOf(CantReadInterviewException.class);
    }
    @Test
    void testInterpret_ShouldThrownUnknownBroadcasterException() {
        var inputString = "?, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [I, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThatThrownBy(() -> lexer.interpret(inputString)).isInstanceOf(UnknownBroadcasterException.class);
    }

    @Test
    void testInterpret_shouldThrowUnknownPartTypeException() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [?, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThatThrownBy(() -> lexer.interpret(inputString)).isInstanceOf(UnknownPartTypeException.class);
    }

    @Test
    void testInterpret_shouldThrowThereMustBeTranslationException() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.),ggg,  T 10.0 15.0 [?, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThatThrownBy(() -> lexer.interpret(inputString)).isInstanceOf(ThereMustBeTranslationException.class);
    }

}