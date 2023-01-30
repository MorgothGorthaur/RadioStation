package dao.lexer;

import org.junit.jupiter.api.Test;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;

import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    Lexer lexer = new LexerImpl();
    @Test
    void testInterpret() {
        var broadcaster = new RadioBroadcaster("Victor", new LinkedHashSet<>(List.of(new WorkOnRadioExperience("radio", 2)
                , new WorkOnRadioExperience("rock", 4))));
        var translation = new TranslationImpl.Builder(5)
                .addPart(new Music("singer", "song", 2.3))
                        .addPart(new Interview("Misha", 1.2))
                                .addPart(new Advertisement("product", 0.8))
                                        .addPart(new Music("another", "g", 0.7)).build();
        var translation_2 = new TranslationImpl.Builder(5).addPart(new Music("singer", "song", 2.3))
                .addPart(new Interview("Vasya", 1.2))
                .addPart(new Advertisement("product", 0.8))
                .addPart(new Music("another", "g", 0.7)).build();
        broadcaster.translations().addAll(List.of(translation, translation_2));
        assertEquals(lexer.interpret(broadcaster.toString()).toString(), broadcaster.toString() );
    }
}