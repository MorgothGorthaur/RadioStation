package translation.part;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import translation.TranslationImpl;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({ @JsonSubTypes.Type(GuestBroadcaster.class), @JsonSubTypes.Type(RadioBroadcaster.class),
        @JsonSubTypes.Type(Music.class), @JsonSubTypes.Type(Interview.class), @JsonSubTypes.Type(Advertisement.class)
})
public interface Part {
    double minuteDuration();
}
