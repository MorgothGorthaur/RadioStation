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
        var name = updated.getName();
        var experiences = updated.getExperiences();
        var line = "";
        printUpdateMenu();
        while (!(line = reader.readLine()).equals("update")) {
            switch (line){
                case "update name" -> name = setName();
                case "update experience" -> experiences.addAll(creator.create());
                case "remove experience" -> experiences.clear();
                default -> printUpdateMenu();
            }
        }
        return new RadioBroadcaster(name, experiences, updated.getTranslations());
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
