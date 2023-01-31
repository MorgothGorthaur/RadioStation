package exception;

public class RadioBroadcasterCreationException extends RuntimeException {
    public RadioBroadcasterCreationException() {
        super("name cant be empty!");
    }
}
