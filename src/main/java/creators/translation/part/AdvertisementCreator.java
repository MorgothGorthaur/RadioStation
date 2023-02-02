package creators.translation.part;

import exception.AdvertisementCreationException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import translation.part.Advertisement;

import java.io.BufferedReader;

@AllArgsConstructor
class AdvertisementCreator implements PartCreator {
    private final BufferedReader reader;
    @Override
    @SneakyThrows
    public Advertisement create() {
        try {
            System.out.print("print product name: ");
            var productName = reader.readLine();
            System.out.print("print duration (in minutes) ");
            var duration = Double.parseDouble(reader.readLine());
            return new Advertisement(productName, duration);
        } catch (AdvertisementCreationException | NumberFormatException ex) {
            System.out.println("\n" + ex.getMessage());
            return create();
        }
    }
}
