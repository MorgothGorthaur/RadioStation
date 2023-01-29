package dao;

import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class DaoImpl implements RadioStationDao {
    private static final String FILE_NAME = "saves";
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
