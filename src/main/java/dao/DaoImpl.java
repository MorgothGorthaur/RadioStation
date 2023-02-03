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
    public void write(List<Broadcaster> broadcasters) {
        try(var output = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            output.writeObject(broadcasters);
        }
    }

    @Override
    public List<Broadcaster> read(){
        try(var input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Broadcaster>) input.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
