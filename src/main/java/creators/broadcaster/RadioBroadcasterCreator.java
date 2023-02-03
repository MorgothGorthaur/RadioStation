package creators.broadcaster;

import creators.broadcaster.experience.ExperienceSetCreator;
import exception.RadioBroadcasterCreationException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;
import java.io.IOException;

class RadioBroadcasterCreator implements BroadcasterCreator {
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
            return new RadioBroadcaster(setName(), creator.create());
        } catch (RadioBroadcasterCreationException ex) {
            System.out.println("\n" + ex.getMessage());
            return create();
        }
    }

    @Override
    @SneakyThrows
    public Broadcaster update(Broadcaster broadcaster) {
        var updated = (RadioBroadcaster) broadcaster;
        var line = "";
        printUpdateMenu();
        while (!(line = reader.readLine()).equals("update")) {
            switch (line){
                case "update name" -> updated.setName(setName());
                case "update experience" -> updated.setExperiences(creator.create());
                default -> printUpdateMenu();
            }
        }
        return updated;
    }

    private void printUpdateMenu() {
        System.out.println("""
                +++++++++++++++++++++++++++++++++++++++++++++++
                +                update menu                  +
                + update name - for updating name             +
                + update experience - for updating experience +
                + update - update                             +
                +++++++++++++++++++++++++++++++++++++++++++++++
                """);
    }
    @SneakyThrows
    private String setName() {
        System.out.print("print radio broadcaster name: ");
        return reader.readLine();
    }
}
