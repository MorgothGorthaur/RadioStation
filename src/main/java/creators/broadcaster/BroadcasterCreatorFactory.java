package creators.broadcaster;

import lombok.RequiredArgsConstructor;
import personality.Broadcaster;

import java.io.BufferedReader;


public class BroadcasterCreatorFactory {
    private final GuestBroadcasterCreator guestBroadcasterCreator;
    private final RadioBroadcasterCreator radioBroadcasterCreator;
    public BroadcasterCreatorFactory(BufferedReader reader) {
        guestBroadcasterCreator = new GuestBroadcasterCreator(reader);
        radioBroadcasterCreator = new RadioBroadcasterCreator(reader);
    }
    public Broadcaster createBroadcaster(BroadcasterType type) {
        return switch (type) {
            case GUEST -> guestBroadcasterCreator.create();
            case RADIO -> ;
        };

    }

    public enum BroadcasterType {
        GUEST,
        RADIO;
    }
}
