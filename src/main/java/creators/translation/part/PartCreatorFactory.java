package creators.translation.part;

import translation.part.Part;

public interface PartCreatorFactory {
    Part createPart(PartType type);

    enum PartType {
        ADVERTISEMENT,
        INTERVIEW,
        MUSIC;
    }
}
