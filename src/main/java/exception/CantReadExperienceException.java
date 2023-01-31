package exception;

public class CantReadExperienceException extends IllegalArgumentException{
    public CantReadExperienceException(String value) {
        super("cant read this experience part! " + value);
    }
}
