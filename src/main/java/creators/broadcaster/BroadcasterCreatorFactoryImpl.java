package creators.broadcaster;

import exception.UnknownBroadcasterException;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;


public class BroadcasterCreatorFactoryImpl implements BroadcasterCreatorFactory{
    private final GuestBroadcasterCreator guestBroadcasterCreator;
    private final RadioBroadcasterCreator radioBroadcasterCreator;
    public BroadcasterCreatorFactoryImpl(BufferedReader reader) {
        guestBroadcasterCreator = new GuestBroadcasterCreator(reader);
        radioBroadcasterCreator = new RadioBroadcasterCreator(reader);
    }
    public Broadcaster create(BroadcasterType type) {
        return switch (type) {
            case GUEST -> guestBroadcasterCreator.create();
            case RADIO -> radioBroadcasterCreator.create();
        };

    }

    public Broadcaster update(Broadcaster broadcaster) {
        if(broadcaster instanceof RadioBroadcaster) return radioBroadcasterCreator.update(broadcaster);
        else if(broadcaster instanceof GuestBroadcaster) return guestBroadcasterCreator.update(broadcaster);
        else throw new UnknownBroadcasterException(broadcaster.toString());
    }

}
