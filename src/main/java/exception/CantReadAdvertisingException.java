package exception;

public class CantReadAdvertisingException extends IllegalArgumentException{
    public CantReadAdvertisingException(String value) {
        super("cant read this advertising part! " + value);
    }
}
