package creators.broadcaster;

import creators.broadcaster.experience.ExperienceCreator;
import creators.broadcaster.experience.ExperienceSetCreator;
import exception.GuestBroadcasterCreationException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;

import java.io.BufferedReader;

@RequiredArgsConstructor
public class GuestBroadcasterCreator implements BroadcasterCreator{
    private final BufferedReader reader;

    @Override
    @SneakyThrows
    public GuestBroadcaster create() {
        try {
            System.out.print("print broadcaster name: ");
            var name = reader.readLine();
            System.out.print("print broadcaster resume: ");
            var resume = reader.readLine();
            return new GuestBroadcaster(name, resume);
        } catch (GuestBroadcasterCreationException ex) {
            System.err.println(ex.getMessage());
            return create();
        }
    }
}
