package creators.broadcaster;
import exception.GuestBroadcasterCreationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.GuestBroadcaster;

import java.io.BufferedReader;

@RequiredArgsConstructor
class GuestBroadcasterCreator implements BroadcasterCreator{
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
