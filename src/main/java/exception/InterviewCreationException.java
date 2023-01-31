package exception;

public class InterviewCreationException extends RuntimeException {
    public InterviewCreationException() {
        super("interviewee must be not empty! duration time must be bigger then 0!");
    }
}
