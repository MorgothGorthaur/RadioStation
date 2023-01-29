package personality;

import lombok.AllArgsConstructor;
import lombok.ToString;
import translation.Translation;


import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@ToString
public class RadioBroadcaster implements Broadcaster {
    private String name;
    private final Set<WorkOnRadioExperience> experience = new LinkedHashSet<>();
    private final Set<Translation> translations = new LinkedHashSet<>();

    public RadioBroadcaster(String name, Set<WorkOnRadioExperience> experiences, Set<Translation> translations) {
        this.name = name;
        addExperiences(experiences);
        addTranslations(translations);
    }

    public void addTranslation(Translation translation) {
        translations.add(translation);
    }

    public void addExperience(WorkOnRadioExperience workOnRadioExperience) {
        experience.add(workOnRadioExperience);
    }

    public void addTranslations(Set<Translation> translations) {
        this.translations.addAll(translations);
    }

    public void addExperiences(Set<WorkOnRadioExperience> experiences) {
        this.experience.addAll(experiences);
    }
}
