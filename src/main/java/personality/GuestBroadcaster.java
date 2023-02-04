package personality;

import exception.GuestBroadcasterCreationException;
import lombok.*;
import translation.Translation;

import java.util.ArrayList;
import java.util.List;

public record GuestBroadcaster(@NonNull String name, @NonNull String resume, @NonNull List<Translation> translations) implements Broadcaster{
    public GuestBroadcaster{
        if(name.equals("") || resume.equals("")) throw new GuestBroadcasterCreationException();
    }
    public GuestBroadcaster(@NonNull String name,@NonNull String resume) {
        this(name, resume, new ArrayList<>());
    }
}
