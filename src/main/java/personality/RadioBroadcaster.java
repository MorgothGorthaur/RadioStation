package personality;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

public record RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperience> experiences, LinkedHashSet<Translation> translations) {
    public RadioBroadcaster(String name, Set<WorkOnRadioExperience> experiences) {
        this(name, new LinkedHashSet<>(experiences), new LinkedHashSet<>());
    }
    public RadioBroadcaster(String name) {
        this(name, new LinkedHashSet<>(), new LinkedHashSet<>());
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