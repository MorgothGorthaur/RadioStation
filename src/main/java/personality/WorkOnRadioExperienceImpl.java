package personality;

import exception.WorkOnRadioExperienceCreationException;
import lombok.NonNull;

public record WorkOnRadioExperienceImpl(@NonNull String stationName, double yearExperience) implements Experience{

    public WorkOnRadioExperienceImpl {
        if(stationName.equals("") || yearExperience <= 0) throw new WorkOnRadioExperienceCreationException();
    }
}
