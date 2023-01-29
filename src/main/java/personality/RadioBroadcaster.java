package personality;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

public record RadioBroadcaster(String name, Set<WorkOnRadioExperience> experiences, Set<Translation> translations) {
    public RadioBroadcaster(String name, Set<WorkOnRadioExperience> experiences) {
        this(name, new LinkedHashSet<>(experiences), new LinkedHashSet<>());
    }
    public RadioBroadcaster(String name) {
        this(name, new LinkedHashSet<>(), new LinkedHashSet<>());
    }
}