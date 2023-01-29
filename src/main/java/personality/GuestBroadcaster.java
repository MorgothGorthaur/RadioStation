package personality;

import translation.Translation;

import java.util.LinkedHashSet;

public record GuestBroadcaster(String name, String resume,
                               LinkedHashSet<Translation> translations) implements Broadcaster {
    public GuestBroadcaster(String name, String resume) {
        this(name, resume, new LinkedHashSet<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(!(o instanceof Broadcaster broadcaster)) return false;
        return name.equals(broadcaster.name());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}