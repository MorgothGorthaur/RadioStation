import creators.broadcaster.BroadcasterCreatorFactory;
import creators.translation.TranslationCreator;
import creators.translation.TranslationCreatorImpl;
import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.Converter;
import dao.lexer.ConverterImpl;
import dao.lexer.Lexer;
import dao.lexer.LexerImpl;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeworkImpl implements HomeWork {
    private final RadioStationDao dao;
    private final BufferedReader reader;
    private final BroadcasterCreatorFactory broadcasterCreatorFactory;
    private final Map<String, Broadcaster> broadcasters;
    private final TranslationCreator translationCreator;

    public HomeworkImpl(String fileName, Lexer lexer, Converter converter, BufferedReader reader) {
        dao = new DaoImpl(fileName, lexer, converter);
        this.reader = reader;
        broadcasterCreatorFactory = new BroadcasterCreatorFactory(reader);
        broadcasters = new HashMap<>(dao.read().stream().collect(Collectors.toMap(Broadcaster::getName, broadcaster -> broadcaster)));
        translationCreator = new TranslationCreatorImpl(reader);
    }

    @Override
    public void addBroadcaster() {
        var createdBroadcaster = createBroadcasterHandler();
        System.out.println("your broadcaster: " + createdBroadcaster);
        addToBroadcasters(createdBroadcaster);
    }

    @Override
    public void addTranslation() {
        var name = setName();
        var broadcaster = broadcasters.get(name);
        if (broadcaster != null) broadcaster.getTranslations().add(translationCreator.create());
        else System.out.println("broadcaster with name " + name + " not founded!");
    }

    @Override
    public void updateBroadcaster() {
        var name = setName();
        var broadcaster = broadcasters.remove(name);
        if (broadcaster != null) { 
            var updated = broadcasterCreatorFactory.updateBroadcaster(broadcaster);
            System.out.println("your broadcaster: " + broadcaster );
            addToBroadcasters(updated);
        }
        else System.out.println("broadcaster with this name " + name + "not founded!");
    }

    @Override
    public void removeBroadcaster() {
        var name = setName();
        var broadcaster = broadcasters.remove(name);
        System.out.println(broadcaster != null ? "removed broadcaster " + broadcaster : "broadcaster with this name " + name + "not founded!");
    }

    @Override
    public void printBroadcasters() {
        System.out.println(broadcasters);
    }

    @Override
    public void printBroadcaster() {
        var name = setName();
        var broadcaster = broadcasters.get(name);
        if(broadcaster != null) getBroadcasterTypeHandler(broadcaster);
        else System.out.println("broadcaster with name " + name + "not founded!");
    }

    @Override
    public void save() {
        dao.write(broadcasters.values());
    }

    private void getBroadcasterTypeHandler(Broadcaster broadcaster) {
        if(broadcaster instanceof GuestBroadcaster guestBroadcaster) getGuestBroadcaster(guestBroadcaster);
        else if(broadcaster instanceof RadioBroadcaster radioBroadcaster) getRadioBroadcaster(radioBroadcaster);
    }

    @SneakyThrows
    private void getRadioBroadcaster(RadioBroadcaster radioBroadcaster) {
        System.out.println(getRadioBroadcasterMenu());
        var line = "";
        while (!(line = reader.readLine()).equals("exit")){
            System.out.println(switch (line) {
                case "print name" ->  radioBroadcaster.getName();
                case "print experiences" -> radioBroadcaster.getExperiences();
                case "print translations" -> radioBroadcaster.getTranslations();
                default -> getRadioBroadcasterMenu();
            });
        }
    }



    @SneakyThrows
    private void getGuestBroadcaster(GuestBroadcaster guestBroadcaster) {
        System.out.println(getGuestBroadcasterMenu());
        var line = "";
        while (!(line = reader.readLine()).equals("exit")) {
            System.out.println(switch (line) {
                case "print name" -> guestBroadcaster.getName();
                case "print resume" -> guestBroadcaster.getResume();
                case "print translation" -> guestBroadcaster.getTranslations();
                default -> getGuestBroadcasterMenu();
            });
        }
    }



    private void addToBroadcasters(Broadcaster broadcaster) {
        if (broadcasters.containsKey(broadcaster.getName())) {
            System.out.println("broadcaster with this name already exist");
            System.out.print("do you want to rewrite?[y/n]");
            if (yesNoHandler()) broadcasters.put(broadcaster.getName(), broadcaster);
        } else broadcasters.put(broadcaster.getName(), broadcaster);
    }

    @SneakyThrows
    private boolean yesNoHandler() {
        return switch (reader.readLine()) {
            case "y" -> true;
            case "n" -> false;
            default -> yesNoHandler();
        };
    }

    @SneakyThrows
    private Broadcaster createBroadcasterHandler() {
        System.out.print("print type [guest/radio]");
        var type = reader.readLine();
        return switch (type) {
            case "guest" -> broadcasterCreatorFactory.createBroadcaster(BroadcasterCreatorFactory.BroadcasterType.GUEST);
            case "radio" -> broadcasterCreatorFactory.createBroadcaster(BroadcasterCreatorFactory.BroadcasterType.RADIO);
            default -> createBroadcasterHandler();
        };
    }

    private String getRadioBroadcasterMenu() {
        return """
                         RadioBroadcaster menu
                print name - for getting name
                print experiences - for getting experiences
                print translations - for getting translations
                exit - returns to main menu
                """;
    }
    private String getGuestBroadcasterMenu(){
        return """
                         GuestBroadcaster menu
                print name - for getting name
                print resume - for getting resume
                print translations - for getting translations
                exit - returns to main menu
                """;
    }

    @SneakyThrows
    private String setName() {
        System.out.print("print broadcaster name: ");
        return reader.readLine();
    }
}
