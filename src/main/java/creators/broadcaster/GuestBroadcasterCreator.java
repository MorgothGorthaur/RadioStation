package creators.broadcaster;

import creators.broadcaster.experience.ExperienceCreator;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.BufferedReader;

@AllArgsConstructor
public class GuestBroadcasterCreator implements BroadcasterCreator{
    private final BufferedReader reader;
    private final ExperienceCreator experienceCreator;
    @Override
    @SneakyThrows
    public Broadcaster create() {
        System.out.print("print broadcaster name: ");
        var name = reader.readLine();
        System.out.print("print broadcaster resume: ");
        var resume = reader.readLine();
        
    }
}
