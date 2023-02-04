package personality;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import exception.RadioBroadcasterCreationException;
import lombok.NonNull;
import personality.experience.Experience;
import translation.Translation;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public record RadioBroadcaster(@NonNull String name,@JsonDeserialize(as=LinkedHashSet.class) @NonNull Set<Experience> experiences,
                               @NonNull List<Translation> translations) implements Broadcaster{
    public RadioBroadcaster{
        if(name.equals("")) throw new RadioBroadcasterCreationException();
    }
    public RadioBroadcaster(@NonNull String name,@NonNull Set<Experience> experiences) {
        this(name, experiences, new ArrayList<>());
    }
}
