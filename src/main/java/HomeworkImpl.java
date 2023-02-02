import creators.broadcaster.BroadcasterCreatorFactory;
import creators.translation.TranslationCreator;
import creators.translation.TranslationCreatorImpl;
import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.ConverterImpl;
import dao.lexer.LexerImpl;
import lombok.SneakyThrows;
import personality.Broadcaster;

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
        addToBroadcasters(createdBroadcaster);
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
            var updated = broadcasterCreatorFactory.updateBroadcaster(broadcaster);
            System.out.println("your broadcaster: " + broadcaster );
            addToBroadcasters(updated);
        }
        else System.out.println("broadcaster with this name " + name + "not founded!");
    }

    @Override
    public void removeBroadcaster(String name) {
        var broadcaster = broadcasters.remove(name);
        System.out.println(broadcaster != null ? "removed broadcaster " + broadcaster : "broadcaster with this name " + name + "not founded!");
    }
    private void addToBroadcasters(Broadcaster broadcaster) {
        if (broadcasters.containsKey(broadcaster.getName())) {
            System.out.println("broadcaster with this name already exist");
            System.out.println("do you want to rewrite?[y/n]");
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
}
