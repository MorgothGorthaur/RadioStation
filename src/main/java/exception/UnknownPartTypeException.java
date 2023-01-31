package exception;

public class UnknownPartTypeException extends IllegalStateException{
    public UnknownPartTypeException(char ch) {
        super("unknown part type! " + ch);
    }
    public UnknownPartTypeException(String part) {
        super("unknown part type! " + part);
    }
}
