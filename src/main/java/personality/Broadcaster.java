package personality;

import translation.Translation;

import java.util.Set;

public interface Broadcaster {
    String getName();
    Set<Translation> getTranslations();
}
