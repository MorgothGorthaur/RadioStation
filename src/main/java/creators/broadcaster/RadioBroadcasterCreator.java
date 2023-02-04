package creators.broadcaster;

import creators.broadcaster.experience.ExperienceSetCreator;
import exception.RadioBroadcasterCreationException;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;

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
        try {
            var updated = (RadioBroadcaster) broadcaster;
            var name = updated.name();
            var experiences = updated.experiences();
            var line = "";
            printUpdateMenu();
            while (!(line = reader.readLine()).equals("update")) {
                switch (line) {
                    case "update name" -> name = setName();
                    case "update experience" -> experiences.addAll(creator.create());
                    case "remove experience" -> experiences.clear();
                    default -> printUpdateMenu();
                }
            }
            return new RadioBroadcaster(name, experiences, updated.translations());
        } catch (RadioBroadcasterCreationException ex) {
            System.out.println(ex.getMessage());
            return update(broadcaster);
        }
    }

    private void printUpdateMenu() {
        System.out.println("""
                +++++++++++++++++++++++++++++++++++++++++++++++++
                +                update menu                    +
                + update name - for updating name               +
                + update experience - for adding new experience +
                + remove experience - for remove all experiences+
                + update - update                               +
                +++++++++++++++++++++++++++++++++++++++++++++++++
                """);
    }
    @SneakyThrows
    private String setName() {
        System.out.print("print radio broadcaster name: ");
        return reader.readLine();
    }
}
