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
        var line = "";
        while (!line.equals("n")) {
            System.out.print("do you want to add experience? [y/n]");
            line = reader.readLine();
            if ("y".equals(line)) experiences.add(experienceCreator.create());
        }

        return experiences;
    }
}
