package translation;

import translation.part.Part;

import java.util.Deque;

public interface Translation {
    double price();
    double minuteDuration();
    Deque<Part> parts();
}
