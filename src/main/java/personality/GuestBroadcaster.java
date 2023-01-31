package personality;

import translation.Translation;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public record GuestBroadcaster(String name, String resume,
                               LinkedHashSet<Translation> translations) implements Broadcaster {
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