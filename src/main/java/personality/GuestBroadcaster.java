package personality;

import exception.GuestBroadcasterCreationException;
import lombok.NonNull;
import translation.Translation;

import java.util.LinkedHashSet;

public record GuestBroadcaster(@NonNull String name,@NonNull String resume,
                               @NonNull LinkedHashSet<Translation> translations) implements Broadcaster {
    public GuestBroadcaster {
        if(name.equals("") || resume.equals("")) throw new GuestBroadcasterCreationException();
    }
    public GuestBroadcaster(String name, String resume) {
        this(name, resume, new LinkedHashSet<>());
    }
    @Override
    public String toString(){
        var str = new StringBuilder("G, " + name + ", (" + resume + "), ");
        for (var translation : translations) str.append(translation).append("|");
        return str.toString();
    }
}