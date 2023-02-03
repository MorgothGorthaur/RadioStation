package translation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;
import translation.part.Part;

import java.util.Deque;
import java.util.List;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(TranslationImpl.class),
})
public interface Translation {
    double price();
    double minuteDuration();
    List<Part> parts();
}
