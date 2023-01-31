package dao.lexer;

import exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;

import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    Lexer lexer = new LexerImpl();
    @Test
    void testInterpret_shouldReturnRadioBroadcaster() {
        var inputString = "R, Victor Tarasov, (E, some radio, 5.0=>E, another radio, 6.0=>), T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThat(lexer.interpret(inputString).toString()).isEqualTo(inputString);
    }
    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withEmptyExperience() {
        var inputString = "R, Victor Tarasov, (), T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThat(lexer.interpret(inputString).toString()).isEqualTo(inputString);
    }

    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withEmptyTranslation() {
        var inputString = "R, Victor Tarasov, (E, some radio, 5.0=>E, another radio, 6.0=>), ";
        assertThat(lexer.interpret(inputString).toString()).isEqualTo(inputString);
    }

    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withTranslation_withoutAnyParts() {
        var inputString = "R, Victor Tarasov, (E, some radio, 5.0=>E, another radio, 6.0=>), T 10.0 15.0 []|";
        assertThat(lexer.interpret(inputString).toString()).isEqualTo(inputString);
    }

    @Test
    void testInterpret_shouldReturnGuestBroadcaster() {
        var inputString = "G, Victor Tarasov, (some resume, @ gg.), T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|";
        assertThat(lexer.interpret(inputString).toString()).isEqualTo(inputString);
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