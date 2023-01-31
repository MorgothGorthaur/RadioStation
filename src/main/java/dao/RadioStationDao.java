package dao;

import personality.Broadcaster;

public interface RadioStationDao {
    void write(Iterable<Broadcaster> broadcasters);
    Iterable<Broadcaster> read();
}
