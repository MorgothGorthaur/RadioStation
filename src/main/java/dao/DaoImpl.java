package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.BreakTree;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.*;
import java.util.*;

public class DaoImpl implements RadioStationDao {
    private final String FILE_NAME;
    private final File file;
    private final ObjectMapper mapper;

    public DaoImpl(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
        file = new File(FILE_NAME);
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
        if (file.isFile()) {
            try (var reader = new BufferedReader(new FileReader(FILE_NAME))) {
                var broadcasters = new ArrayList<Broadcaster>();
                var line = "";
                while ((line = reader.readLine()) != null) broadcasters.add(mapper.readValue(line, Broadcaster.class));
                return broadcasters;
            }
        } return new ArrayList<>();
    }
}
