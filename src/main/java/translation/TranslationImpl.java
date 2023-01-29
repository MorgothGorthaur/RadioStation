package translation;

import lombok.Getter;
import lombok.ToString;
import translation.part.CommercialPart;
import translation.part.Part;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

@ToString
@Getter
public class TranslationImpl implements Translation {
    private double price;

    private double minuteDuration;
    private final Deque<Part> parts = new ArrayDeque<>();

    private TranslationImpl(){}
    @Override
    public Deque<Part> getParts() {
        return new ArrayDeque<>(parts);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if(!(o instanceof TranslationImpl translation)) return false;
        return price == translation.price && minuteDuration == translation.minuteDuration
                && parts.equals(translation.parts);
    }

    @Override
    public int hashCode() {
        var hash = 7;
        hash += 31 * hash + Objects.hashCode(price);
        hash += 31 * hash + Objects.hashCode(minuteDuration);
        hash += 31 * hash + parts.hashCode();
        return hash;
    }

    public static class Builder {
        private final double minuteDuration;
        private double freeTime;
        private double commercialTime = 0;
        private double price = 0;
        private final Deque<Part> parts = new ArrayDeque<>();
        public Builder(double minuteDuration) {
            this.minuteDuration = minuteDuration;
            this.freeTime = minuteDuration;
        }
        public Builder addPart(Part part) {
            freeTime -= part.minuteDuration();
            if(part instanceof CommercialPart commercialPart) {
                price += commercialPart.getPrice();
                commercialTime += commercialPart.minuteDuration();
            }
            checkConditions();
        }

        private void checkConditions() {
            if(freeTime < 0) throw new RuntimeException();
            if(commercialTime > minuteDuration /2) throw new RuntimeException();
        }
    }
}