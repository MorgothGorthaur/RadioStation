package dao.lexer;

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

}