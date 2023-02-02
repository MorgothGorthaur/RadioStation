import creators.broadcaster.BroadcasterCreatorFactory;
import personality.Broadcaster;

public interface HomeWork {
    void addBroadcaster(BroadcasterCreatorFactory.BroadcasterType type);

    void addTranslation(String name);

    void updateBroadcaster(String name);

    void removeBroadcaster(String name);


}
