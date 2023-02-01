package dao.creators.broadcaster;

import personality.Broadcaster;
import personality.RadioBroadcaster;

public class BroadcasterCreatorFactory {
    private final GuestBroadcasterCreator guestBroadcasterCreator;
    private final RadioBroadcasterCreator radioBroadcasterCreator;
    public BroadcasterCreatorFactory() {

    }
    public Broadcaster createBroadcaster(BroadcasterType type) {
        switch (type) {
            case GUEST ->;
            case RADIO -> ;
        }

    }

    public enum BroadcasterType {
        GUEST,
        RADIO;
    }
}
