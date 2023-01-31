package personality;

import exception.WorkOnRadioExperienceCreationException;
import lombok.NonNull;

public record WorkOnRadioExperience(@NonNull String stationName,@NonNull double yearExperience) {

    public WorkOnRadioExperience{
        if(stationName.equals("") || yearExperience <= 0) throw new WorkOnRadioExperienceCreationException();
    }
    @Override
    public String toString() {
        return "E, " + stationName + ", " + yearExperience;
    }
}
