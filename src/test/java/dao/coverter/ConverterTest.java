package dao.coverter;

import exception.*;
import org.junit.jupiter.api.Test;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.experience.WorkOnRadioExperienceImpl;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Music;
import translation.part.Part;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    Converter converter = new ConverterImpl();
    @Test
    void testInterpret_shouldReturnRadioBroadcaster() {
        var expected = "R, Victor Tarasov, (E, some radio, 5.0|E, another radio, 6.0|), (T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|)\n";
        var broadcaster = new RadioBroadcaster("Victor Tarasov",
                new LinkedHashSet<>(List.of(new WorkOnRadioExperienceImpl("some radio", 5),
                        new WorkOnRadioExperienceImpl("another radio", 6))),
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<>(
                        List.of(new Music("some singer","some music", 5.0), new Advertisement("some product",5.0),
                                new Music("another singer", "another music", 5.0)))))));
        assertThat(converter.convert(List.of(broadcaster))).isEqualTo(expected);
    }
    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withEmptyExperience() {
        var expected = "R, Victor Tarasov, (), (T 10.0 15.0 [M, some singer, some music, 5.0=>A, some product, 5.0=>M, another singer, another music, 5.0=>]|)\n";
        var broadcaster = new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(), new LinkedHashSet<Translation>(List.of(
                new TranslationImpl(10.0, 15.0, new ArrayList<>(
                        List.of(new Music("some singer", "some music", 5.0),
                                new Advertisement("some product", 5.0),
                                new Music("another singer", "another music", 5.0)))))));
        assertThat(converter.convert(List.of(broadcaster))).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withEmptyTranslation() {
        var expected = "R, Victor Tarasov, (E, some radio, 5.0|E, another radio, 6.0|), ()\n";
        var broadcaster = new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(
                List.of(new WorkOnRadioExperienceImpl("some radio", 5.0),
                        new WorkOnRadioExperienceImpl("another radio", 6.0))));
        assertThat(converter.convert(List.of(broadcaster))).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldReturnRadioBroadcaster_withTranslation_withoutAnyParts() {
        var expected = "R, Victor Tarasov, (E, some radio, 5.0|E, another radio, 6.0|), (T 10.0 15.0 []|)\n";
        var broadcaster = new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(
                List.of(new WorkOnRadioExperienceImpl("some radio", 5.0),
                        new WorkOnRadioExperienceImpl("another radio", 6.0))),
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<Part>()))));
        assertThat(converter.convert(List.of(broadcaster))).isEqualTo(expected);
    }

    @Test
    void testInterpret_shouldReturnGuestBroadcaster() {
        var expected = "G, Victor Tarasov, (some resume, @ gg.), (T 10.0 15.0 [M, some singer, some music, 5.0=>]|)\n";
        var broadcaster = new GuestBroadcaster("Victor Tarasov", "some resume, @ gg.",
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<>(List.of(new Music("some singer", "some music", 5.0)))))));
        assertThat(converter.convert(List.of(broadcaster))).isEqualTo(expected);
    }
}