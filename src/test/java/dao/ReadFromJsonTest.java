package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.experience.WorkOnRadioExperienceImpl;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadFromJsonTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void convertJsonToMusic() throws JsonProcessingException {
        var music = "{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0}";
        var expected = new Music("singer", "song", 20);
        assertThat(mapper.readValue(music, Music.class)).isEqualTo(expected);
    }

    @Test
    void convertJsonToInterview() throws JsonProcessingException {
        var interview = "{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0}";
        var expected = new Interview("person", 5);
        assertThat(mapper.readValue(interview, Interview.class)).isEqualTo(expected);
    }

    @Test
    void convertJsonToAdvertisement() throws JsonProcessingException {
        var advertisement = "{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}";
        var expected = new Advertisement("product", 6);
        assertThat(mapper.readValue(advertisement, Part.class)).isEqualTo(expected);
    }

    @Test
    void convertJsonToTranslation() throws JsonProcessingException {
        var translation  ="{\"@type\":\"TranslationImpl\",\"price\":180.0,\"minuteDuration\":20.0,\"parts\":[{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0},{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0},{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}]}";
        var expected = new TranslationImpl(180, 20,
                new ArrayList<>(List.of(new Music("singer", "song", 20),
                        new Interview("person", 5), new Advertisement("product", 6))));
        assertThat(mapper.readValue(translation, Translation.class)).isEqualTo(expected);
    }

    @Test
    void convertExperienceToJson() throws JsonProcessingException {
        var experience = "{\"@type\":\"WorkOnRadioExperienceImpl\",\"stationName\":\"radio\",\"yearExperience\":5.0}";
        var expected = new WorkOnRadioExperienceImpl("radio", 5);
        assertThat(mapper.writeValueAsString(experience)).isEqualTo(expected);
    }

    @Test
    void convertGuestBroadcasterToJson() throws JsonProcessingException {
        var guest = new GuestBroadcaster("name", "resume", new LinkedHashSet<>(List.of(
                new TranslationImpl(180, 20,
                        new ArrayList<>(List.of(new Music("singer", "song", 20),
                                new Interview("person", 5), new Advertisement("product", 6))))
        )));
        var expected = "{\"@type\":\"GuestBroadcaster\",\"name\":\"name\",\"resume\":\"resume\",\"translations\":[{\"@type\":\"TranslationImpl\",\"price\":180.0,\"minuteDuration\":20.0,\"parts\":[{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0},{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0},{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}]}]}";
        assertThat(mapper.writeValueAsString(guest)).isEqualTo(expected);
    }

    @Test
    void convertRadioBroadcasterToJson() throws JsonProcessingException {
        var radio = new RadioBroadcaster("name", new LinkedHashSet<>(List.of(new WorkOnRadioExperienceImpl("radio", 5))),
                new LinkedHashSet<>(List.of(
                        new TranslationImpl(180, 20,
                                new ArrayList<>(List.of(new Music("singer", "song", 20),
                                        new Interview("person", 5), new Advertisement("product", 6))))
                )));
        var expected ="{\"@type\":\"RadioBroadcaster\",\"name\":\"name\",\"experiences\":[{\"@type\":\"WorkOnRadioExperienceImpl\",\"stationName\":\"radio\",\"yearExperience\":5.0}],\"translations\":[{\"@type\":\"TranslationImpl\",\"price\":180.0,\"minuteDuration\":20.0,\"parts\":[{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0},{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0},{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}]}]}";
        assertThat(mapper.writeValueAsString(radio)).isEqualTo(expected);
    }
}
