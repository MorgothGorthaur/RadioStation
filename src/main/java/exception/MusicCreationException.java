package exception;

public class MusicCreationException extends RuntimeException {
    public MusicCreationException() {
        super("music and singer name must be not empty! duration time must be bigger then 0");
    }
}
