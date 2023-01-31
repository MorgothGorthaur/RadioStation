package translation.part;

import exception.MusicCreationException;
import lombok.NonNull;

public record Music(@NonNull String singerName,@NonNull String musicName, double minuteDuration) implements Part {
    public Music {
        if(singerName.equals("") || musicName.equals("") || minuteDuration <= 0) throw new MusicCreationException();
    }
    @Override
    public String toString() {
        return "M, " + singerName + ", " + musicName + ", " + minuteDuration;
    }
}

