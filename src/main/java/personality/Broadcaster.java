package personality;

import translation.Translation;

import java.util.LinkedHashSet;
import java.util.Set;

public interface Broadcaster {
    String name();
    Set<Translation> translations();
}
