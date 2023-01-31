package personality;
import exception.RadioBroadcasterCreationException;
import lombok.NonNull;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

public record RadioBroadcaster(@NonNull String name,@NonNull LinkedHashSet<WorkOnRadioExperience> experiences,@NonNull LinkedHashSet<Translation> translations) implements Broadcaster {
    public RadioBroadcaster(String name, Set<WorkOnRadioExperience> experiences) {
        this(name, new LinkedHashSet<>(experiences), new LinkedHashSet<>());
    }

    public RadioBroadcaster {
        if(name.equals("")) throw new RadioBroadcasterCreationException();
    }

    @Override
    public String toString() {
        var str = new StringBuilder("R, " + name + ", (");
        for (var exp : experiences) str.append(exp).append("=>");
        str.append("), ");
        for (var translation : translations) str.append(translation).append("|");
        return str.toString();
    }
}