package creators.broadcaster.experience;

import exception.WorkOnRadioExperienceCreationException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import personality.WorkOnRadioExperienceImpl;

import java.io.BufferedReader;

@AllArgsConstructor
class ExperienceCreatorImpl implements ExperienceCreator{
    private final BufferedReader reader;
    @Override
    @SneakyThrows
    public WorkOnRadioExperienceImpl create() {
        try {
            System.out.print("print station name ");
            var stationName = reader.readLine();
            System.out.print("print duration (in years) ");
            var duration = Double.parseDouble(reader.readLine());
            return new WorkOnRadioExperienceImpl(stationName, duration);
        } catch (WorkOnRadioExperienceCreationException | NumberFormatException ex) {
            System.out.println("\n" + ex.getMessage());
            return create();
        }
    }
}
