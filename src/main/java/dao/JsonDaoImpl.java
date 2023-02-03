package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.*;
import java.util.*;

public class JsonDaoImpl implements RadioStationDao {
    private final String FILE_NAME;
    private final ObjectMapper mapper;

    public JsonDaoImpl(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
        mapper = new ObjectMapper();
    }

    @Override
    @SneakyThrows
    public void write(List<Broadcaster> broadcasters) {
        try(var writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (var br : broadcasters) writer.append(mapper.writeValueAsString(br));
        }
    }

    @Override
    @SneakyThrows
    public List<Broadcaster> read() {
        if (new File(FILE_NAME).isFile()) {
            try (var reader = new BufferedReader(new FileReader(FILE_NAME))) {
                var broadcasters = new ArrayList<Broadcaster>();
                var line = "";
                while ((line = reader.readLine()) != null) broadcasters.add(mapper.readValue(line, Broadcaster.class));
                return broadcasters;
            }
        } return new ArrayList<>();
    }
}
