package translation.part;

import exception.AdvertisementCreationException;
import lombok.NonNull;

import java.io.Serializable;

public record Advertisement(@NonNull String productName, double minuteDuration, double price) implements CommercialPart {

    public Advertisement {
        if(productName.equals("") || minuteDuration <= 0) throw new AdvertisementCreationException();
    }
    public Advertisement(@NonNull String productName, double minuteDuration) {
        this(productName, minuteDuration, 5 * minuteDuration);
    }

}
