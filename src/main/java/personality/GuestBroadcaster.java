package personality;

import exception.GuestBroadcasterCreationException;
import lombok.*;
import translation.Translation;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class GuestBroadcaster implements Broadcaster{
    @NonNull
    private String name;
    @NonNull
    private String resume;
    @NonNull
    private Set<Translation> translations;
    public GuestBroadcaster( String name,String resume,Set<Translation> translations) {
        if(name.equals("") || resume.equals("")) throw new GuestBroadcasterCreationException();
        this.name = name;
        this.resume = resume;
        this.translations = translations;
    }
    public GuestBroadcaster(String name, String resume) {
        this(name, resume, new LinkedHashSet<>());
    }
}