package creators.translation.part;

import exception.MusicCreationException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import translation.part.Music;

import java.io.BufferedReader;

@AllArgsConstructor
class MusicCreator implements PartCreator {
    private final BufferedReader reader;
    @SneakyThrows
    @Override
    public Music create() {
        try {
            System.out.print("print music name ");
            var musicName = reader.readLine();
            System.out.print("print singer name ");
            var singerName = reader.readLine();
            System.out.print("print get duration (in minutes)");
            var duration = Double.parseDouble(reader.readLine());
            return new Music(singerName, musicName, duration);
        } catch (MusicCreationException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return create();
        }
    }
}
