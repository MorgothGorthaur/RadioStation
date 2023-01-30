package dao;

import org.junit.jupiter.api.Test;
import personality.Broadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Music;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RadioStationDaoTest {
    RadioStationDao dao = new DaoImpl("save");
    @Test
    void write() {
        var br = new RadioBroadcaster("F", new LinkedHashSet<>(List.of(new WorkOnRadioExperience("f", 5))));
        var translation = new TranslationImpl.Builder(5).addPart(new Music("g", "G", 3)).addPart(new Music("6","6", 2)).build();
        br.translations().add(translation);
        dao.write(new ArrayDeque<>(List.of(br, br)));

    }

    @Test
    void read() {
    }
}