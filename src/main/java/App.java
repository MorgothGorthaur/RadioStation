import dao.DaoImpl;
import dao.RadioStationDao;
import dao.lexer.LexerImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    private final RadioStationDao dao = new DaoImpl("save", new LexerImpl());
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        var broadcasters = new ArrayList<>(dao.read());
        
    }
}
