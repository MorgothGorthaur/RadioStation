package exception;

public class CantReadMusicException extends IllegalArgumentException{
    public CantReadMusicException(String music) {
        super("cant read this music part! " + music);
    }
}
