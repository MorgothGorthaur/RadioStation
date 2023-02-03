
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        new App().run();
    }

    @SneakyThrows
    private void run() {
        System.out.print("print file name: ");
        var homework = new HomeworkImpl(reader.readLine(), reader);
        printMainMenu();
        var line = "";
        while (!(line = reader.readLine()).equals("exit")) mainMenuHandler(homework, line);
        homework.save();
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
                        main menu
                add broadcaster - for adding broadcaster
                add translation - for adding translation
                update broadcaster - for updating broadcaster
                remove broadcaster - for removing broadcaster
                print broadcasters - for printing all broadcasters
                print broadcaster - for printing broadcaster
                """);
    }
}
