import dao.DaoImpl;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class App {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        new App().run();
    }

    @SneakyThrows
    private void run() {
        System.out.print("print save-file name: ");
        var command = reader.readLine().strip();
        var dao = new DaoImpl(command);
        var broadcasters = new LinkedHashSet<>();
        System.out.println(getMenu());
        while (!command.equals("exit")) {
            command = reader.readLine().strip().replaceAll("\\s+", "");
        }

    }

    private String getMenu() {
        return """
                menu - reprints menu
                exit - ends program
                """;
    }
}
