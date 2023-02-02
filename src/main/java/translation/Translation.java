package translation;

import translation.part.Part;

import java.util.Deque;
import java.util.List;

public interface Translation {
    double price();
    double minuteDuration();
    List<Part> parts();
}
