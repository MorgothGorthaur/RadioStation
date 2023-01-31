package exception;

public class GuestBroadcasterCreationException extends RuntimeException{
    public GuestBroadcasterCreationException() {
        super("name and resume cant be empty!");
    }
}
