package creators.broadcaster;

import personality.Broadcaster;

public interface BroadcasterCreatorFactory {
    Broadcaster create(BroadcasterType type);
    Broadcaster update(Broadcaster broadcaster);
    enum BroadcasterType {
        GUEST,
        RADIO;
    }
}
