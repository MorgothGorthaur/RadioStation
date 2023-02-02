package personality;

import exception.GuestBroadcasterCreationException;
import lombok.*;
import translation.Translation;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NonNull
public class GuestBroadcaster implements Broadcaster{
    private String name;
    private String resume;
    private Set<Translation> translations;
    public GuestBroadcaster(String name, String resume, Set<Translation> translations) {
        if(name.equals("") || resume.equals("")) throw new GuestBroadcasterCreationException();
        this.name = name;
        this.resume = resume;
        this.translations = translations;
    }
    public GuestBroadcaster(String name, String resume) {
        this(name, resume, new LinkedHashSet<>());
    }
}