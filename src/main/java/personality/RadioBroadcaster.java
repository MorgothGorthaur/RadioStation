package personality;
import exception.RadioBroadcasterCreationException;
import lombok.Data;
import lombok.NonNull;
import translation.Translation;


import java.util.LinkedHashSet;

@Data
@NonNull
public class RadioBroadcaster implements Broadcaster{
    private String name;
    private LinkedHashSet<WorkOnRadioExperienceImpl> experiences;
    private LinkedHashSet<Translation> translations;
    public RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperienceImpl> experiences, LinkedHashSet<Translation> translations) {
        if(name.equals("")) throw new RadioBroadcasterCreationException();
        this.name = name;
        this.translations = translations;
        this.experiences = experiences;
    }
    public RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperienceImpl> experiences) {
        this(name, experiences, new LinkedHashSet<>());
    }
}