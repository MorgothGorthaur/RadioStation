package dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
@RequiredArgsConstructor
public class DaoImpl implements RadioStationDao {
    private final String FILE_NAME;
    @Override
    @SneakyThrows
    public void write(Deque<Broadcaster> broadcasters){
        try(var writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.append(broadcasters.toString());
        }
    }

    @Override
    public List<Broadcaster> read() {
     return null;
    }

}
