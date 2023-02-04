package exception;

public class TranslationCreationException extends RuntimeException {
    public TranslationCreationException() {
        super("duration time must be bigger then 0!");
    }
}
