package creators.translation.part;

import translation.part.Part;

import java.io.BufferedReader;

public class PartCreatorFactory {
    private final AdvertisementCreator advertisementCreator;
    private final InterviewCreator interviewCreator;
    private final MusicCreator musicCreator;
    public PartCreatorFactory(BufferedReader reader) {
        advertisementCreator = new AdvertisementCreator(reader);
        interviewCreator = new InterviewCreator(reader);
        musicCreator = new MusicCreator(reader);
    }
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

