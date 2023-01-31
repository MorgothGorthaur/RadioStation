package dao;

import personality.Broadcaster;

import java.util.Collection;
import java.util.List;

public interface RadioStationDao {
    void write(Collection<Broadcaster> broadcasters);
    Collection<Broadcaster> read();
}
