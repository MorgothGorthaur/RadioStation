package exception;

public class ThereMustBeTranslationException extends RuntimeException{
    public ThereMustBeTranslationException(String value) {
        super("there must be a translation! " + value);
    }
}
