package creators.broadcaster;

import exception.UnknownBroadcasterException;
import lombok.RequiredArgsConstructor;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;

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
            case RADIO -> radioBroadcasterCreator.create();
        };

    }

    public Broadcaster updateBroadcaster(Broadcaster broadcaster) {
        if(broadcaster instanceof RadioBroadcaster) return radioBroadcasterCreator.update(broadcaster);
        else if(broadcaster instanceof GuestBroadcaster) return guestBroadcasterCreator.update(broadcaster);
        else throw new UnknownBroadcasterException(broadcaster.toString());
    }

    public enum BroadcasterType {
        GUEST,
        RADIO;
    }
}
