package dao;

import personality.Broadcaster;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

public interface RadioStationDao {
    void write(List<Broadcaster> broadcasters);
    List<Broadcaster> read() throws FileNotFoundException;
}
