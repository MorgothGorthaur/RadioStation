package dao.lexer;

import personality.Broadcaster;

public interface Converter {
    String convert(Iterable<Broadcaster> broadcasters);
}
