package creators.broadcaster;
import exception.GuestBroadcasterCreationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;

import java.io.BufferedReader;
import java.io.IOException;

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
        var guest = (GuestBroadcaster) broadcaster;
        pintUpdateMenu();
        var line = "";
        while (!(line = reader.readLine()).equals("update")) {
            switch (line) {
                case "update name" -> guest.setName(setName());
                case "update resume" -> guest.setResume(setResume());
                default -> pintUpdateMenu();
            }
        }
        return guest;
    }

    private void pintUpdateMenu() {
        System.out.println("""
                        update menu
                update name - for updating name
                update resume - for updating resume
                update - update
                """);
    }
    @SneakyThrows
    private String setResume(){
        System.out.print("print broadcaster resume: ");
        return reader.readLine();
    }

    @SneakyThrows
    private String setName() {
        System.out.print("print broadcaster name: ");
        return reader.readLine();
    }
}
