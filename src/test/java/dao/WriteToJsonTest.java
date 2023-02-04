package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.experience.WorkOnRadioExperienceImpl;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WriteToJsonTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Test
    void convertMusicToJson() throws JsonProcessingException {
        var music = new Music("singer", "song", 20);
        var expected = "{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0}";
        assertThat(mapper.writeValueAsString(music)).isEqualTo(expected);
    }

    @Test
    void convertInterviewToJson() throws JsonProcessingException {
        var interview = new Interview("person", 5);
        var expected = "{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0}";
        assertThat(mapper.writeValueAsString(interview)).isEqualTo(expected);
    }

    @Test
    void convertAdvertisementToJson() throws JsonProcessingException {
        var advertisement = new Advertisement("product", 6);
        var expected = "{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}";
        assertThat(mapper.writeValueAsString(advertisement)).isEqualTo(expected);
    }

    @Test
    void convertTranslationToJson() throws JsonProcessingException {
        var translation = new TranslationImpl(180, 20,
                new ArrayList<>(List.of(new Music("singer", "song", 20),
                        new Interview("person", 5), new Advertisement("product", 6))));
        var expected  ="{\"@type\":\"TranslationImpl\",\"price\":180.0,\"minuteDuration\":20.0,\"parts\":[{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0},{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0},{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}]}";
        assertThat(mapper.writeValueAsString(translation)).isEqualTo(expected);
    }

    @Test
    void convertExperienceToJson() throws JsonProcessingException {
        var experience = new WorkOnRadioExperienceImpl("radio", 5);
        var expected = "{\"@type\":\"WorkOnRadioExperienceImpl\",\"stationName\":\"radio\",\"yearExperience\":5.0}";
        assertThat(mapper.writeValueAsString(experience)).isEqualTo(expected);
    }

    @Test
    void convertGuestBroadcasterToJson() throws JsonProcessingException {
        var guest = new GuestBroadcaster("name", "resume", new ArrayList<>(List.of(
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
                new ArrayList<>(List.of(
                        new TranslationImpl(180, 20,
                                new ArrayList<>(List.of(new Music("singer", "song", 20),
                                        new Interview("person", 5), new Advertisement("product", 6))))
                )));
        var expected ="{\"@type\":\"RadioBroadcaster\",\"name\":\"name\",\"experiences\":[{\"@type\":\"WorkOnRadioExperienceImpl\",\"stationName\":\"radio\",\"yearExperience\":5.0}],\"translations\":[{\"@type\":\"TranslationImpl\",\"price\":180.0,\"minuteDuration\":20.0,\"parts\":[{\"@type\":\"Music\",\"singerName\":\"singer\",\"musicName\":\"song\",\"minuteDuration\":20.0},{\"@type\":\"Interview\",\"interviewee\":\"person\",\"minuteDuration\":5.0,\"price\":150.0},{\"@type\":\"Advertisement\",\"productName\":\"product\",\"minuteDuration\":6.0,\"price\":30.0}]}]}";
        assertThat(mapper.writeValueAsString(radio)).isEqualTo(expected);
    }
}
