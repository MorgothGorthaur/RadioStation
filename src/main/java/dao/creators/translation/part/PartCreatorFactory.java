package dao.creators.translation.part;

import lombok.AllArgsConstructor;
import translation.part.Part;

@AllArgsConstructor
public class PartCreatorFactory {
    private AdvertisementCreator advertisementCreator;
    private InterviewCreator interviewCreator;
    private MusicCreator musicCreator;
    public Part createPart(PartType type) {
        return switch (type) {
            case ADVERTISEMENT ->  advertisementCreator.create();
            case MUSIC -> musicCreator.create();
            case INTERVIEW -> interviewCreator.create();
        };
    }

    public enum PartType {
        ADVERTISEMENT,
        INTERVIEW,
        MUSIC;
    }
}

