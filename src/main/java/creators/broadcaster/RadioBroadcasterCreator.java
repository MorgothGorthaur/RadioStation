package creators.broadcaster;

import creators.broadcaster.experience.ExperienceSetCreator;
import exception.RadioBroadcasterCreationException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;

public class RadioBroadcasterCreator implements BroadcasterCreator{
    private final BufferedReader reader;
    private final ExperienceSetCreator creator;
    public RadioBroadcasterCreator(BufferedReader reader) {
        this.reader = reader;
        creator = new ExperienceSetCreator(reader);
    }
    @Override
    @SneakyThrows
    public RadioBroadcaster create() {
        try {
            System.out.print("print broadcaster name");
            var name = reader.readLine();
            var experiences = creator.create();
            return new RadioBroadcaster(name, experiences);
        } catch (RadioBroadcasterCreationException ex){
            System.err.println(ex.getMessage());
            return create();
        }
    }
}
