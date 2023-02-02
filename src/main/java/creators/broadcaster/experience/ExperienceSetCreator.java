package creators.broadcaster.experience;

import lombok.SneakyThrows;
import personality.Experience;

import java.io.BufferedReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class ExperienceSetCreator {
    private final ExperienceCreator experienceCreator;
    private final BufferedReader reader;
    public ExperienceSetCreator(BufferedReader reader) {
        this.reader = reader;
        experienceCreator = new ExperienceCreatorImpl(reader);
    }
    @SneakyThrows
    public Set<Experience> create() {
        var experiences = new LinkedHashSet<Experience>();
        System.out.print("do you want to add experience? [y/n]");
        var line = "";
        while (!(line = reader.readLine()).equals("n")) {
            switch (line) {
                case "y" -> experiences.add(experienceCreator.create());
                case "n" -> {}
                default -> System.out.print("do you want to add experience? [y/n]");
            }
        }

        return experiences;
    }
}
