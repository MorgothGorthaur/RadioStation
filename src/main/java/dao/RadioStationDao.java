package dao;

import personality.Broadcaster;

import java.util.Deque;
import java.util.List;

public interface RadioStationDao {
    void write(Deque<Broadcaster> broadcasters);
    List<Broadcaster> read();
}
