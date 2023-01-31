package personality;
import exception.RadioBroadcasterCreationException;
import lombok.NonNull;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

public record RadioBroadcaster(@NonNull String name,@NonNull LinkedHashSet<WorkOnRadioExperience> experiences,@NonNull LinkedHashSet<Translation> translations) implements Broadcaster {
    public RadioBroadcaster(String name, LinkedHashSet<WorkOnRadioExperience> experiences) {
        this(name, experiences, new LinkedHashSet<>());
    }
    public RadioBroadcaster {
        if(name.equals("")) throw new RadioBroadcasterCreationException();
    }
    public LinkedHashSet<WorkOnRadioExperience> experiences() {
        return new LinkedHashSet<>(experiences);
    }
}