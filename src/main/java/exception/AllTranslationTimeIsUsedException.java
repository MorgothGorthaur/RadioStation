package exception;

public class AllTranslationTimeIsUsedException extends RuntimeException {
    public AllTranslationTimeIsUsedException() {
        super("all translation time is used!");
    }
}
