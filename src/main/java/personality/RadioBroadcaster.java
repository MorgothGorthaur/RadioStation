package personality;
import exception.RadioBroadcasterCreationException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import personality.experience.Experience;
import translation.Translation;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class RadioBroadcaster implements Broadcaster{
    @NonNull
    private String name;
    @NonNull
    private Set<Experience> experiences;
    @NonNull
    private List<Translation> translations;
    public RadioBroadcaster(String name,Set<Experience> experiences,List<Translation> translations) {
        if(name.equals("")) throw new RadioBroadcasterCreationException();
        this.name = name;
        this.translations = translations;
        this.experiences = experiences;
    }
    public RadioBroadcaster(String name, Set<Experience> experiences) {
        this(name, experiences, new ArrayList<>());
    }
}