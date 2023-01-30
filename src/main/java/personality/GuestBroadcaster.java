package personality;

import translation.Translation;

import java.util.LinkedHashSet;

public record GuestBroadcaster(String name, String resume,
                               LinkedHashSet<Translation> translations) implements Broadcaster {
    public GuestBroadcaster(String name, String resume) {
        this(name, resume, new LinkedHashSet<>());
    }
    
}