import creators.broadcaster.BroadcasterCreatorFactory;
import creators.broadcaster.BroadcasterCreatorFactoryImpl;
import creators.translation.TranslationCreator;
import creators.translation.TranslationCreatorImpl;
import dao.RadioStationDao;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeworkImpl implements HomeWork {
    private final RadioStationDao dao;
    private final BufferedReader reader;
    private final BroadcasterCreatorFactory broadcasterCreatorFactory;
    private final Map<String, Broadcaster> broadcasters;
    private final TranslationCreator translationCreator;

    @SneakyThrows
    public HomeworkImpl(RadioStationDao dao,BufferedReader reader) {
        this.dao = dao;
        this.reader = reader;
        broadcasterCreatorFactory = new BroadcasterCreatorFactoryImpl(reader);
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
            var updated = broadcasterCreatorFactory.update(broadcaster);
            System.out.println("your broadcaster: " + broadcaster );
            addToBroadcasters(updated);
        }
        else System.out.println("broadcaster with this name " + name + " not founded!");
    }

    @Override
    public void removeBroadcaster() {
        var name = setName();
        var broadcaster = broadcasters.remove(name);
        System.out.println(broadcaster != null ? "removed broadcaster " + broadcaster : "broadcaster with this name " + name + " not founded!");
    }

    @Override
    public void printBroadcasters() {
        broadcasters.values().forEach(System.out::println);
    }

    @Override
    public void printBroadcaster() {
        var name = setName();
        var broadcaster = broadcasters.get(name);
        if(broadcaster != null) getBroadcasterTypeHandler(broadcaster);
        else System.out.println("broadcaster with name " + name + " not founded!");
    }

    @Override
    public void save() {
        dao.write(new ArrayList<>(broadcasters.values()));
    }

    private void getBroadcasterTypeHandler(Broadcaster broadcaster) {
        if(broadcaster instanceof GuestBroadcaster guestBroadcaster) getGuestBroadcaster(guestBroadcaster);
        else if(broadcaster instanceof RadioBroadcaster radioBroadcaster) getRadioBroadcaster(radioBroadcaster);
    }

    @SneakyThrows
    private void getRadioBroadcaster(RadioBroadcaster radioBroadcaster) {
        printRadioBroadcasterMenu();
        var line = "";
        while (!(line = reader.readLine()).equals("exit")){
            switch (line) {
                case "print name" -> System.out.println(radioBroadcaster.getName());
                case "print experiences" -> radioBroadcaster.getExperiences().forEach(System.out::println);
                case "print translations" -> radioBroadcaster.getTranslations().forEach(System.out::println);
                default -> printRadioBroadcasterMenu();
            }
        }
    }



    @SneakyThrows
    private void getGuestBroadcaster(GuestBroadcaster guestBroadcaster) {
        printGuestBroadcasterMenu();
        var line = "";
        while (!(line = reader.readLine()).equals("exit")) {
            switch (line) {
                case "print name" -> System.out.println(guestBroadcaster.getName());
                case "print resume" -> System.out.println(guestBroadcaster.getResume());
                case "print translations" -> guestBroadcaster.getTranslations().forEach(System.out::println);
                default -> printGuestBroadcasterMenu();
            }
        }
    }



    private void addToBroadcasters(Broadcaster broadcaster) {
        if (broadcasters.containsKey(broadcaster.getName()) && rewriteBroadcaster()) broadcasters.put(broadcaster.getName(), broadcaster);
        else broadcasters.put(broadcaster.getName(), broadcaster);
    }

    @SneakyThrows
    private boolean rewriteBroadcaster() {
        System.out.println("broadcaster with this name already exist");
        System.out.print("do you want to rewrite?[y/n]");
        return switch (reader.readLine()) {
            case "y" -> true;
            case "n" -> false;
            default -> rewriteBroadcaster();
        };
    }

    @SneakyThrows
    private Broadcaster createBroadcasterHandler() {
        System.out.print("print type [guest/radio]: ");
        var type = reader.readLine();
        return switch (type) {
            case "guest" -> broadcasterCreatorFactory.create(BroadcasterCreatorFactory.BroadcasterType.GUEST);
            case "radio" -> broadcasterCreatorFactory.create(BroadcasterCreatorFactory.BroadcasterType.RADIO);
            default -> createBroadcasterHandler();
        };
    }

    private void printRadioBroadcasterMenu() {
        System.out.println("""
                +++++++++++++++++++++++++++++++++++++++++++++++++
                +         RadioBroadcaster menu                 +
                + print name - for getting name                 +
                + print experiences - for getting experiences   +
                + print translations - for getting translations +
                + exit - returns to main menu                   +
                +++++++++++++++++++++++++++++++++++++++++++++++++
                """);
    }
    private void printGuestBroadcasterMenu(){
        System.out.println("""
                +++++++++++++++++++++++++++++++++++++++++++++++++                     
                +         GuestBroadcaster menu                 +
                + print name - for getting name                 +
                + print resume - for getting resume             +
                + print translations - for getting translations +
                + exit - returns to main menu                   +
                +++++++++++++++++++++++++++++++++++++++++++++++++
                """);
    }

    @SneakyThrows
    private String setName() {
        System.out.print("print broadcaster name: ");
        return reader.readLine();
    }
}
