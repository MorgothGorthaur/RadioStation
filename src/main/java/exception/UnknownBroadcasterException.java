package exception;

public class UnknownBroadcasterException extends IllegalStateException {
    public UnknownBroadcasterException(String broadcaster) {
        super("unknown broadcaster type! " + broadcaster);
    }
}
