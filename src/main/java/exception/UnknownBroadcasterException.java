package exception;

public class UnknownBroadcasterException extends IllegalStateException {
    public UnknownBroadcasterException(char ch) {
        super("unknown broadcaster type! " + ch);
    }
    public UnknownBroadcasterException(String broadcaster) {
        super("unknown broadcaster type! " + broadcaster);
    }
}
