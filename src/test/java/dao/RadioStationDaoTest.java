package dao;

import dao.lexer.ConverterImpl;
import dao.lexer.LexerImpl;
import org.junit.jupiter.api.Test;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperienceImpl;
import translation.TranslationImpl;
import translation.part.Music;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.List;

class RadioStationDaoTest {
    RadioStationDao dao = new DaoImpl("save", new LexerImpl(), new ConverterImpl());
    @Test
    void write() {
        var br = new RadioBroadcaster("F", new LinkedHashSet<>(List.of(new WorkOnRadioExperienceImpl("f", 5))));
        var translation = new TranslationImpl.Builder(5).addPart(new Music("g", "G", 3)).addPart(new Music("6","6", 2)).build();
        var translation_2 = new TranslationImpl.Builder(5).addPart(new Music("g", "d", 3)).addPart(new Music("6","6", 2)).build();
        br.getTranslations().addAll(List.of(translation, translation_2));
        dao.write(new ArrayDeque<>(List.of(br, br)));

    }

    @Test
    void read() {
    }
}