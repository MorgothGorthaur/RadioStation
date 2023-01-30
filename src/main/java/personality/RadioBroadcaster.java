package personality;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

public record RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperience> experiences, LinkedHashSet<Translation> translations) implements Broadcaster {
    public RadioBroadcaster(String name, Set<WorkOnRadioExperience> experiences) {
        this(name, new LinkedHashSet<>(experiences), new LinkedHashSet<>());
    }
    public RadioBroadcaster(String name) {
        this(name, new LinkedHashSet<>(), new LinkedHashSet<>());
    }

    @Override
    public String toString() {
        return "R " + name + " " + experiences + " " + translations;
    }
}