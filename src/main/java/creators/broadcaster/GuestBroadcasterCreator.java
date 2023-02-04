package creators.broadcaster;
import exception.GuestBroadcasterCreationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;

import java.io.BufferedReader;

@RequiredArgsConstructor
class GuestBroadcasterCreator implements BroadcasterCreator{
    private final BufferedReader reader;

    @Override
    public GuestBroadcaster create() {
        try {
            return new GuestBroadcaster(setName(), setResume());
        } catch (GuestBroadcasterCreationException ex) {
            System.out.println("\n" + ex.getMessage());
            return create();
        }
    }
    @Override
    @SneakyThrows
    public Broadcaster update(Broadcaster broadcaster) {
        try {
            var guest = (GuestBroadcaster) broadcaster;
            var name = guest.name();
            var resume = guest.resume();
            pintUpdateMenu();
            var line = "";
            while (!(line = reader.readLine()).equals("update")) {
                switch (line) {
                    case "update name" -> name = setName();
                    case "update resume" -> resume = setResume();
                    default -> pintUpdateMenu();
                }
            }
            return new GuestBroadcaster(name, resume);
        } catch (GuestBroadcasterCreationException ex) {
            System.out.println(ex.getMessage());
            return update(broadcaster);
        }
    }

    private void pintUpdateMenu() {
        System.out.println("""
                +++++++++++++++++++++++++++++++++++++++
                +             update menu             +
                + update name - for updating name     +
                + update resume - for updating resume +
                + update - update                     +
                +++++++++++++++++++++++++++++++++++++++
                """);
    }
    @SneakyThrows
    private String setResume(){
        System.out.print("print guest broadcaster resume: ");
        return reader.readLine();
    }

    @SneakyThrows
    private String setName() {
        System.out.print("print guest broadcaster name: ");
        return reader.readLine();
    }
}
