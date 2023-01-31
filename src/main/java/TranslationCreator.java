import exception.*;
import lombok.SneakyThrows;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TranslationCreator {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public Translation createTranslation() {
        try {
            System.out.println(getTranslationMenu());
            var builder = new TranslationImpl.Builder(getTimeDuration());
            var line = "";
            while (!(line = reader.readLine()).equals("build")) translationMenuHandler(builder, line);
            return builder.build();
        } catch (TranslationCreationException ex) {
            System.err.println(ex.getMessage());
            return createTranslation();
        }

    }

    private void translationMenuHandler(TranslationImpl.Builder builder, String line) {
        try {
            switch (line) {
                case "music" -> builder.addPart(createMusic());
                case "interview" -> builder.addPart(createInterview());
                case "advertising" -> builder.addPart(createAdvertising());
                case "build" -> System.out.println("built");
                case "get free time" -> System.out.println(builder.getFreeTime());
                case "get price" -> System.out.println(builder.getPrice());
                case "get commercial parts time" -> System.out.println(builder.getCommercialTime());
                default -> System.out.println(getTranslationMenu());
            }
        } catch (TooBigCommercialTimeException | AllTranslationTimeIsUsedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @SneakyThrows
    private Advertisement createAdvertising() {
        try {
            System.out.print("print product name: ");
            var name = reader.readLine();
            var duration = getTimeDuration();
            return new Advertisement(name, duration);
        } catch (AdvertisementCreationException ex) {
            System.err.println(ex.getMessage());
            return createAdvertising();
        }
    }

    @SneakyThrows
    private Interview createInterview() {
        try {
            System.out.print("print interviewee name: ");
            var name = reader.readLine();
            var duration = getTimeDuration();
            return new Interview(name, duration);
        } catch (InterviewCreationException ex) {
            System.err.println(ex.getMessage());
            return createInterview();
        }
    }

    @SneakyThrows
    private Music createMusic() {
        try {
            System.out.print("print singer name:");
            var singerName = reader.readLine();
            System.out.print("print music name:");
            var musicName = reader.readLine();
            var duration = getTimeDuration();
            return new Music(singerName, musicName, duration);
        } catch (MusicCreationException ex) {
            System.err.println(ex.getMessage());
            return createMusic();
        }
    }

    @SneakyThrows
    private double getTimeDuration(){
        try {
            System.out.print("print time duration (in minutes)");
            return Double.parseDouble(reader.readLine());
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return getTimeDuration();
        }

    }

    private String getTranslationMenu() {
        return """                        
                        Translation creation menu:
                music - for create music
                interview - for create interview
                advertising - for create advertising
                get free time - for getting free time of translation
                get price - for getting price of translation
                get commercial parts time - for getting time of commercial parts
                menu - reprints menu
                build - ends creation
                """;
    }
}
