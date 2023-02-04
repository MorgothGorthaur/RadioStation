
import dao.DaoFactory;
import dao.DaoFactoryImpl;
import dao.RadioStationDao;
import dao.StreamDaoImpl;
import lombok.SneakyThrows;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import translation.part.Interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {new App().run();}

    @SneakyThrows
    private void run() {
        var homework = new HomeworkImpl(chooseDao(),reader);
        printMainMenu();
        var line = "";
        while (!(line = reader.readLine()).equals("exit")) mainMenuHandler(homework, line);
        homework.save();
    }
    @SneakyThrows
    private RadioStationDao chooseDao() {
        var factory = new DaoFactoryImpl();
        System.out.print("print file name: ");
        var fileName = reader.readLine();
        System.out.print("json or byte?");
        return switch (reader.readLine()) {
            case "json" -> factory.getDao(DaoFactory.DaoType.JSON, fileName);
            case "byte" -> factory.getDao(DaoFactory.DaoType.STREAM, fileName);
            default -> chooseDao();
        };
    }

    private void mainMenuHandler(HomeworkImpl homework, String line) {
        switch (line) {
            case "add broadcaster" -> homework.addBroadcaster();
            case "add translation" -> homework.addTranslation();
            case "update broadcaster" -> homework.updateBroadcaster();
            case "remove broadcaster" -> homework.removeBroadcaster();
            case "print broadcasters" -> homework.printBroadcasters();
            case "print broadcaster" -> homework.printBroadcaster();
            default -> printMainMenu();
        }
    }

    private void printMainMenu() {
        System.out.println("""
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++        
                +                   main menu                        +
                + add broadcaster - for adding broadcaster           +
                + add translation - for adding translation           +
                + update broadcaster - for updating broadcaster      +
                + remove broadcaster - for removing broadcaster      + 
                + print broadcasters - for printing all broadcasters +
                + print broadcaster - for printing broadcaster       +
                + exit - ends program                                +
                ++++++++++++++++++++++++++++++++++++++++++++++++++++++
                """);
    }
}
