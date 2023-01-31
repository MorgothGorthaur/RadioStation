package dao;

import dao.lexer.Lexer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

@RequiredArgsConstructor
public class DaoImpl implements RadioStationDao {
    private final String FILE_NAME;
    private final Lexer lexer;

    @Override
    @SneakyThrows
    public void write(Iterable<Broadcaster> broadcasters) {
        try (var writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (var broadcaster : broadcasters) writer.append(broadcaster.toString()).append("\n");
        }
    }

    @Override
    @SneakyThrows
    public Iterable<Broadcaster> read() {
        try (var reader = new BufferedReader(new FileReader(FILE_NAME))) {
            var broadcasters = new ArrayDeque<Broadcaster>();
            var line = "";
            while ((line = reader.readLine()) != null) broadcasters.add(lexer.interpret(line));
            return broadcasters;
        }

    }
}
