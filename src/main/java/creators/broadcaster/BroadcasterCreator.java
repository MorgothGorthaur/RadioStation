package creators.broadcaster;

import personality.Broadcaster;

interface BroadcasterCreator {
    Broadcaster create();

    Broadcaster update(Broadcaster broadcaster);
}
