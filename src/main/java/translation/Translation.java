package translation;

import translation.part.Part;

import java.util.Deque;

public interface Translation {
    double getPrice();
    double getMinuteDuration();
    Deque<Part> getParts();
}
