package dao.coverter;

import personality.Broadcaster;

public interface Converter {
    String convert(Iterable<Broadcaster> broadcasters);
}
