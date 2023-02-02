package exception;

public class UnknownExperienceTypeException extends RuntimeException {
    public UnknownExperienceTypeException(String string) {
        System.out.println("unknown experience type " + string);
    }
}
