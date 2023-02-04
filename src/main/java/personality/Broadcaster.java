package personality;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import translation.Translation;

import java.io.Serializable;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({ @JsonSubTypes.Type(GuestBroadcaster.class), @JsonSubTypes.Type(RadioBroadcaster.class),
})
public interface Broadcaster extends Serializable {
    String name();
    List<Translation> translations();
}
