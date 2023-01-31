package personality;

import exception.WorkOnRadioExperienceCreationException;
import lombok.NonNull;

public record WorkOnRadioExperience(@NonNull String stationName, double yearExperience) {

    public WorkOnRadioExperience{
        if(stationName.equals("") || yearExperience <= 0) throw new WorkOnRadioExperienceCreationException();
    }
}
