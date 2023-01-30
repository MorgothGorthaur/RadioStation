package exception;

public class CantReadInterviewException extends IllegalArgumentException{
    public CantReadInterviewException(String value) {
        super("cant read this interview part! " + value);
    }
}
