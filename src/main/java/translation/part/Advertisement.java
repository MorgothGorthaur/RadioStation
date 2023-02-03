package translation.part;

import exception.AdvertisementCreationException;
import lombok.NonNull;

import java.io.Serializable;

public record Advertisement(@NonNull String productName, double minuteDuration) implements CommercialPart {
    @Override
    public double getPrice() {
        return 5 * minuteDuration;
    }

    public Advertisement {
        if(productName.equals("") || minuteDuration <= 0) throw new AdvertisementCreationException();
    }

}
