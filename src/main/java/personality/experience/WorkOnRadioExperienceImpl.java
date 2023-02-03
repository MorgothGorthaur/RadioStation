package personality.experience;

import exception.WorkOnRadioExperienceCreationException;
import lombok.NonNull;
import personality.experience.Experience;

import java.io.Serializable;

public record WorkOnRadioExperienceImpl(@NonNull String stationName, double yearExperience) implements Experience, Serializable {

    public WorkOnRadioExperienceImpl {
        if(stationName.equals("") || yearExperience <= 0) throw new WorkOnRadioExperienceCreationException();
    }
}
