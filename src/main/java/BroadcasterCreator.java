import exception.GuestBroadcasterCreationException;
import exception.RadioBroadcasterCreationException;
import exception.WorkOnRadioExperienceCreationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

@RequiredArgsConstructor
@Getter
public class BroadcasterCreator {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    @SneakyThrows
    public Broadcaster createBroadcaster() {
        try {
            System.out.print("print new broadcaster name: ");
            return createBroadcaster(reader.readLine());
        } catch (GuestBroadcasterCreationException | RadioBroadcasterCreationException ex) {
            System.err.println(ex.getMessage());
            return createBroadcaster();
        }
    }

    @SneakyThrows
    private Broadcaster createBroadcaster(String name) {
        System.out.print("does he works in this station? [y/n]");
        return switch (reader.readLine()) {
            case "y" -> new RadioBroadcaster(name, createExperienceSet());
            case "n" -> createGuestBroadcaster(name);
            default -> createBroadcaster(name);
        };
    }

    @SneakyThrows
    private GuestBroadcaster createGuestBroadcaster(String name) {
        System.out.print("print some small resume about guest (1 line only)");
        return new GuestBroadcaster(name, reader.readLine());
    }

    @SneakyThrows
    private LinkedHashSet<WorkOnRadioExperience> createExperienceSet() {
        var set = new LinkedHashSet<WorkOnRadioExperience>();
        var line = "";
        while (!line.equals("n")) {
            System.out.print("do you want to add some experience? [y/n]");
            line = reader.readLine();
            switch (line) {
                case "y" -> set.add(createWorkOnRadioExperience());
                case "n" -> {
                }
                default -> set.addAll(createExperienceSet());
            }
        }
        return set;
    }

    @SneakyThrows
    private WorkOnRadioExperience createWorkOnRadioExperience() {
        try {
            System.out.print("print station name:");
            var name = reader.readLine();
            var duration = getTimeDuration();
            return new WorkOnRadioExperience(name, duration);
        } catch (WorkOnRadioExperienceCreationException ex) {
            System.err.println(ex.getMessage());
            return createWorkOnRadioExperience();
        }
    }

    @SneakyThrows
    private double getTimeDuration() {
        try {
            System.out.print("print time duration (in years)");
            return Double.parseDouble(reader.readLine());
        } catch (NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return getTimeDuration();
        }

    }
}
