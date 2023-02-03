package personality;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;

import java.io.Serializable;
import java.util.Set;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({ @JsonSubTypes.Type(GuestBroadcaster.class), @JsonSubTypes.Type(RadioBroadcaster.class),
})
public interface Broadcaster extends Serializable {
    String getName();
    Set<Translation> getTranslations();
}
