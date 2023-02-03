package dao;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
public class DaoImpl implements RadioStationDao {
    private final String FILE_NAME;
    @Override
    @SneakyThrows
    public void write(Collection<Broadcaster> broadcasters) {
        try(var output = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            output.writeObject(broadcasters);
        }
    }

    @Override
    @SneakyThrows
    public Collection<Broadcaster> read() {
        try(var input = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (Collection<Broadcaster>) input.readObject();
        }
    }
}
