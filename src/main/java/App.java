import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.LexerImpl;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;
import translation.part.Part;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    private final RadioStationDao dao = new DaoImpl("save", new LexerImpl());
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        new App().run();
    }

    @SneakyThrows
    private void run() {
        var broadcasters = new HashMap<>(dao.read().stream().collect(Collectors.toMap(Broadcaster::name, broadcaster -> broadcaster)));
        var line = "";
        printMenu();
        while (!(line = reader.readLine()).equals("exit")) {
            switch (line) {
                case "add translation" -> {
                    System.out.println("print broadcaster name:");
                    var name = reader.readLine();
                    var broadcaster = broadcasters.containsKey(name) ? broadcasters.get(name) : createNewBroadcaster(name);
                    System.out.println(broadcaster);
                    broadcaster.translations().add(createTranslation());
                }
                case "menu" -> printMenu();
                case "exit" -> System.out.println("exit");
            }
        }
    }

    @SneakyThrows
    private Translation createTranslation() {
        System.out.println("print translation time duration: (in minutes)");
        var line = reader.readLine();
        printTranslationMenu();
        var translationBuilder = new TranslationImpl.Builder(Double.parseDouble(line));
        while (!(line = reader.readLine()).equals("build")) {
            switch (line) {
                case "music" -> translationBuilder.addPart(createMusic());
                case "interview" -> translationBuilder.addPart(createInterview());
                case "advertising" -> translationBuilder.addPart(createAdvertising());
                case "build" -> System.out.println("built!");
                default -> printTranslationMenu();
            }

        }
        var translation = translationBuilder.build();
        System.out.println(translation);
        return translation;
    }

    @SneakyThrows
    private Part createAdvertising() {
        System.out.println("print product name: ");
        var name = reader.readLine();
        System.out.println("print time duration");
        var duration = Double.parseDouble(reader.readLine());
        return new Advertisement(name, duration);
    }

    @SneakyThrows
    private Part createInterview() {
        System.out.println("print interviewee name: ");
        var name = reader.readLine();
        System.out.println("print time duration: ");
        var duration = Double.parseDouble(reader.readLine());
        return new Interview(name, duration);
    }

    @SneakyThrows
    private Part createMusic() {
        System.out.println("print singer name:");
        var singerName = reader.readLine();
        System.out.println("print music name:");
        var musicName = reader.readLine();
        System.out.println("print time duration:(minutes)");
        var duration = Double.parseDouble(reader.readLine());
        return new Music(singerName, musicName, duration);

    }

    private void printTranslationMenu() {
        System.out.println("""
                translation menu
                music -> for creating music
                interview -> for creating interview
                advertisement -> for creating advertisement
                """);
    }

    @SneakyThrows
    private Broadcaster createNewBroadcaster(String name) {
        System.out.println("Does he works on this radio? [y/n]");
        return switch (reader.readLine()) {
            case "y" -> createRadioBroadcaster(name);
            case "n" -> createGuestBroadcaster(name);
            default -> createNewBroadcaster(name);
        };
    }

    private RadioBroadcaster createRadioBroadcaster(String name) {
        var broadcaster = new RadioBroadcaster(name);
        broadcaster.experiences().addAll(createExperienceSet());
        return broadcaster;
    }

    @SneakyThrows
    private LinkedHashSet<WorkOnRadioExperience> createExperienceSet() {
        var set = new LinkedHashSet<WorkOnRadioExperience>();
        System.out.println("do you want to add some experience? [y/n]");
        var line = "";
        while (!(line = reader.readLine()).equals("n")) {
            switch (line) {
                case "y" -> set.add(createWorkOnRadioExperience());
                default -> set.addAll(createExperienceSet());
            }
        }
        return set;
    }

    @SneakyThrows
    private WorkOnRadioExperience createWorkOnRadioExperience() {
        System.out.println("print station name:");
        var name = reader.readLine();
        System.out.println("print time duration (in years)");
        var duration = Double.parseDouble(reader.readLine());
        return new WorkOnRadioExperience(name, duration);

    }

    @SneakyThrows
    private Broadcaster createGuestBroadcaster(String name) {
        System.out.println("print some small resume about guest (1 line only)");
        return new GuestBroadcaster(name, reader.readLine());
    }


    public void printMenu() {
        System.out.println("""
                menu
                add translation - for adding new translation
                menu - reprints menu
                exit - ends program
                """);
    }
}
