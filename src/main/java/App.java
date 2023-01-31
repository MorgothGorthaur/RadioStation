import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.ConverterImpl;
import dao.lexer.LexerImpl;
import exception.*;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    private final RadioStationDao dao = new DaoImpl("save", new LexerImpl(), new ConverterImpl());
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        new App().run();
    }

    @SneakyThrows
    private void run() {
        var broadcasterCreator = new LazyBroadcasterCreator(
                new HashMap<>(dao.read().stream().collect(Collectors.toMap(Broadcaster::name, broadcaster -> broadcaster))));
        var translationCreator = new TranslationCreator();
        printMainMenu();
        var line = "";
        while (!(line = reader.readLine()).equals("exit")) mainMenuHandler(broadcasterCreator, translationCreator, line);
        dao.write(broadcasterCreator.getBroadcasters().values());
    }

    @SneakyThrows
    private void mainMenuHandler(LazyBroadcasterCreator broadcasterCreator, TranslationCreator translationCreator, String line) {
        switch (line) {
            case "add translation" -> {
                var broadcaster = broadcasterCreator.getBroadcaster();
                System.out.println("your broadcaster: " + broadcaster);
                var translation = translationCreator.createTranslation();
                System.out.println("your translation: ");
                System.out.println(translation);
                broadcaster.translations().add(translation);
                printMainMenu();
            }
            case "get broadcasters" -> System.out.println(broadcasterCreator.getBroadcasters().keySet());
            case "get broadcaster" -> System.out.println("your broadcaster: " + broadcasterCreator.getBroadcaster());
            case "exit" -> System.out.println("exit");
            default -> printMainMenu();
        }
    }
    public void printMainMenu() {
        System.out.println("""
                menu
                add translation - for adding new translation
                get broadcaster - for getting/adding broadcaster
                get broadcasters - for getting broadcasters names
                menu - reprints menu
                exit - ends program
                """);
    }
}
