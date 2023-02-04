package translation.part;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import translation.TranslationImpl;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({@JsonSubTypes.Type(Music.class), @JsonSubTypes.Type(Interview.class), @JsonSubTypes.Type(Advertisement.class)
})
public interface Part extends Serializable {
    double minuteDuration();
}
