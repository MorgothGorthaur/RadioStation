package personality;
import exception.RadioBroadcasterCreationException;
import lombok.Data;
import lombok.NonNull;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NonNull
public class RadioBroadcaster implements Broadcaster{
    private String name;
    private LinkedHashSet<WorkOnRadioExperience> experiences;
    private LinkedHashSet<Translation> translations;
    public RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperience> experiences, LinkedHashSet<Translation> translations) {
        if(name.equals("")) throw new RadioBroadcasterCreationException();
        this.name = name;
        this.translations = translations;
        this.experiences = experiences;
    }
    public RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperience> experiences) {
        this(name, experiences, new LinkedHashSet<>());
    }
}