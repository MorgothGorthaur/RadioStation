import creators.broadcaster.BroadcasterCreatorFactory;
import personality.Broadcaster;

public interface HomeWork {
    void addBroadcaster();

    void addTranslation(String name);

    void updateBroadcaster(String name);

    void removeBroadcaster(String name);

    void printBroadcasters();

    void printBroadcaster(String name);

    void save();


}
