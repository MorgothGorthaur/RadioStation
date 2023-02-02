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
    private Set<Experience> experiences;
    private Set<Translation> translations;
    public RadioBroadcaster(String name, Set<Experience> experiences, Set<Translation> translations) {
        if(name.equals("")) throw new RadioBroadcasterCreationException();
        this.name = name;
        this.translations = translations;
        this.experiences = experiences;
    }
    public RadioBroadcaster(String name, Set<Experience> experiences) {
        this(name, experiences, new LinkedHashSet<>());
    }
}