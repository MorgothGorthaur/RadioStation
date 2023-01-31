package dao;

import personality.Broadcaster;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;

public interface RadioStationDao {
    void write(Iterable<Broadcaster> broadcasters);
    List<Broadcaster> read();
}
