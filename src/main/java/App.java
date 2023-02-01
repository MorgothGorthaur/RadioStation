import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.ConverterImpl;
import dao.lexer.LexerImpl;
import exception.GuestBroadcasterCreationException;
import exception.RadioBroadcasterCreationException;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    private final RadioStationDao dao = new DaoImpl("save", new LexerImpl(), new ConverterImpl());
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final BroadcasterCreator broadcasterCreator = new BroadcasterCreator();
    private final TranslationCreator translationCreator = new TranslationCreator();

    public static void main(String[] args) {
        new App().run();
    }

    @SneakyThrows
    private void run() {
        var broadcasters = new HashMap<>(dao.read().stream().collect(Collectors.toMap(Broadcaster::getName, broadcaster -> broadcaster)));
        System.out.println(getMainMenu());
        var line = "";
        while (!(line = reader.readLine()).equals("exit"))
            mainMenuHandler(line, broadcasters);
        dao.write(broadcasters.values());
    }

    @SneakyThrows
    private Optional<Broadcaster> getBroadcasterByName(HashMap<String, Broadcaster> broadcasters) {
        System.out.print("print broadcaster name: ");
        return Optional.ofNullable(broadcasters.get(reader.readLine()));
    }

    private void mainMenuHandler(String line, HashMap<String, Broadcaster> broadcasters) {
        switch (line) {
            case "add translation" -> addTranslationHandler(broadcasters);
            case "get broadcasters" -> System.out.println(broadcasters.keySet());
            case "add broadcaster" -> addBroadcasterHandler(broadcasters);
            case "get broadcaster menu" -> {
                var foundBroadcaster = getBroadcasterByName(broadcasters);
                if (foundBroadcaster.isPresent()) broadcasterMenuHandler(foundBroadcaster.get());
                else System.out.println("broadcaster not found!");
            }
            case "exit" -> System.out.println("exit");
            default -> System.out.println(getMainMenu());
        }
    }


    @SneakyThrows
    private void broadcasterMenuHandler(Broadcaster broadcaster) {
        var line = "";
        System.out.println(getBroadcasterMenu());
        while (!(line = reader.readLine()).equals("exit")) {
            System.out.println(switch (line) {
                case "translations" -> broadcaster.getTranslations();
                case "experience" -> broadcaster instanceof RadioBroadcaster radioBroadcaster
                        ? radioBroadcaster.getExperiences() : "this is guest broadcaster!";
                case "resume" -> broadcaster instanceof GuestBroadcaster guestBroadcaster
                        ? guestBroadcaster.getResume() : "this broadcaster works on radio!";
                case "exit" -> "exit";
                default -> getBroadcasterMenu();
            });
        }
        System.out.println(getMainMenu());
    }

    @SneakyThrows
    private void addBroadcasterHandler(HashMap<String, Broadcaster> broadcasters) {
        var broadcaster = broadcasterCreator.createBroadcaster();
        if (broadcasters.containsKey(broadcaster.getName())) {
            System.out.println("broadcaster with this name already exists. Do you want to rewrite? print \"y\"");
            var answer = reader.readLine();
            System.out.println(answer);
            if (!answer.equals("y")) System.out.println("canceled!");
        }
        broadcasters.put(broadcaster.getName(), broadcaster);
        System.out.println("new broadcaster " + broadcaster);
    }

    @SneakyThrows
    private void addTranslationHandler(HashMap<String, Broadcaster> broadcasters) {
        var foundBroadcaster = getBroadcasterByName(broadcasters);
        if (foundBroadcaster.isPresent()) {
            var broadcaster = foundBroadcaster.get();
            var translation = translationCreator.createTranslation();
            broadcaster.getTranslations().add(translation);
            System.out.println("your translation: " + translation);
        } else System.out.println("broadcaster not found!");
    }


    public String getMainMenu() {
        return """
                                   menu
                add translation - for adding new translation
                add broadcaster - for adding broadcaster
                get broadcasters - for getting broadcasters names
                get broadcaster menu - for getting to broadcaster menu
                menu - reprints menu
                exit - ends program
                """;
    }

    private String getBroadcasterMenu() {
        return """
                translations -  for getting all broadcaster transactions;
                experience - for getting all broadcaster experience (but if he is a guest it prints that)
                resume - for getting broadcaster resume (but if he works on radio, it prints that)
                exit - exit
                """;
    }
}
