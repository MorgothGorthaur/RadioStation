import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.experience.WorkOnRadioExperienceImpl;
import translation.TranslationImpl;
import translation.part.Music;
import translation.part.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Check {
    @SneakyThrows
    public static void main(String[] args) {
        var broadcaster =  new RadioBroadcaster("Victor Tarasov", new LinkedHashSet<>(
                List.of(new WorkOnRadioExperienceImpl("some radio", 5.0),
                        new WorkOnRadioExperienceImpl("another radio", 6.0))),
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<Part>()))));
        var guest =  new GuestBroadcaster("Victor Tarasov", "some resume, @ gg.",
                new LinkedHashSet<>(List.of(new TranslationImpl(10.0, 15.0, new ArrayList<>(List.of(new Music("some singer", "some music", 5.0)))))));
        var original = new ArrayList<>(List.of(broadcaster, guest));
        try(var out = new ObjectOutputStream(new FileOutputStream("check"))){

            out.writeObject(original);
        }

        try(var inp = new ObjectInputStream(new FileInputStream("check"))){
            var lst = (List< Broadcaster>) inp.readObject();
            for (var br : lst) System.out.println(br.getClass());
            System.out.println(original.equals(lst));
        }

    }
}
