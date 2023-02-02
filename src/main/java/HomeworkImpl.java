import creators.broadcaster.BroadcasterCreatorFactory;
import creators.translation.TranslationCreator;
import creators.translation.TranslationCreatorImpl;
import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.ConverterImpl;
import dao.lexer.LexerImpl;
import exception.UnknownBroadcasterException;
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

    public HomeworkImpl(BufferedReader reader) {
        dao = new DaoImpl("save", new LexerImpl(), new ConverterImpl());
        this.reader = reader;
        broadcasterCreatorFactory = new BroadcasterCreatorFactory(reader);
        broadcasters = new HashMap<>(dao.read().stream().collect(Collectors.toMap(Broadcaster::getName, broadcaster -> broadcaster)));
        translationCreator = new TranslationCreatorImpl(reader);
    }

    @Override
    public void addBroadcaster() {
        var createdBroadcaster = createBroadcasterHandler();
        System.out.println("your broadcaster: " + createdBroadcaster);
        if (broadcasters.containsKey(createdBroadcaster.getName())) {
            System.out.println("broadcaster with this name already exist");
            System.out.println("do you want to rewrite?[y/n]");
            if (yesNoHandler()) broadcasters.put(createdBroadcaster.getName(), createdBroadcaster);
        } else broadcasters.put(createdBroadcaster.getName(), createdBroadcaster);
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

    @Override
    public void addTranslation(String name) {
        var broadcaster = broadcasters.get(name);
        if (broadcaster != null) broadcaster.getTranslations().add(translationCreator.create());
        else System.out.println("broadcaster with name " + name + " not founded!");
    }

    @Override
    public void updateBroadcaster(String name) {
        var broadcaster = broadcasters.remove(name);
        if (broadcaster != null) {
            var updated = broadcasterUpdateHandler(broadcaster);
        }
        else System.out.println("broadcaster with this name " + name + "not founded!");
    }

    private Broadcaster broadcasterUpdateHandler(Broadcaster broadcaster) {
        if(broadcaster instanceof GuestBroadcaster guestBroadcaster) return updateGuestBroadcaster(guestBroadcaster);
        else if(broadcaster instanceof RadioBroadcaster radioBroadcaster) return updateRadioBroadcaster(radioBroadcaster);
        else throw new UnknownBroadcasterException(broadcaster.toString());
    }

    private RadioBroadcaster updateRadioBroadcaster(RadioBroadcaster radioBroadcaster) {
        return null;
    }

    @SneakyThrows
    private GuestBroadcaster updateGuestBroadcaster(GuestBroadcaster guestBroadcaster) {
        var line = "";
        while (!(line = reader.readLine()).equals("update")) {
            switch (line) {
                case "change name" -> {
                    System.out.print("print name: ");
                    var name = reader.readLine();
                    guestBroadcaster.setName(name);
                }
                case "change resume" -> {
                    System.out.print("print resume ");
                    var resume = reader.readLine();
                    guestBroadcaster.setResume(resume);
                }
                case "u"
            }
        }
    }


    @Override
    public void removeBroadcaster(String name) {
        var broadcaster = broadcasters.remove(name);
        System.out.println(broadcaster != null ? "removed broadcaster " + broadcaster : "broadcaster with this name " + name + "not founded!");
    }
}
