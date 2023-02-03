package personality.experience;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({ @JsonSubTypes.Type(WorkOnRadioExperienceImpl.class)
})
public interface Experience {
}
