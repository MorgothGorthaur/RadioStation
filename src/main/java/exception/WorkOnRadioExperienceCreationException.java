package exception;

import personality.WorkOnRadioExperience;

public class WorkOnRadioExperienceCreationException extends RuntimeException {
    public WorkOnRadioExperienceCreationException() {
    super("radio station name must be not empty! duration must be bigger then 0!");
    }
}
