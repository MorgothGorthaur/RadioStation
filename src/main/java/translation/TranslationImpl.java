package translation;

import exception.AllTranslationTimeIsUsedException;
import exception.TooBigCommercialTimeException;
import exception.TranslationCreationException;
import lombok.Getter;
import translation.part.CommercialPart;
import translation.part.Part;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public record TranslationImpl(double price, double minuteDuration, List<Part> parts) implements Translation{
    @Override
    public List<Part> parts() {
        return new ArrayList<>(parts);
    }

    public static class Builder {
        private final double minuteDuration;
        @Getter
        private double freeTime;
        @Getter
        private double commercialTime = 0;
        @Getter
        private double price = 0;
        private final List<Part> parts = new ArrayList<>();

        public Builder(double minuteDuration) {
            if (minuteDuration <= 0) throw new TranslationCreationException();
            this.minuteDuration = minuteDuration;
            this.freeTime = minuteDuration;
        }

        public Builder addPart(Part part) {
            freeTime -= part.minuteDuration();
            if (part instanceof CommercialPart commercialPart) {
                price += commercialPart.price();
                commercialTime += commercialPart.minuteDuration();
            }
            parts.add(part);
            return this;
        }


        public Builder addParts(Iterable<Part> parts) {
            parts.forEach(this::addPart);
            return this;
        }

        public Translation build() {
            checkConditions();
            return new TranslationImpl(price, minuteDuration, parts);
        }

        private void checkConditions() {
            if(freeTime < 0) throw new AllTranslationTimeIsUsedException();
            if(commercialTime > minuteDuration /2) throw new TooBigCommercialTimeException(commercialTime, minuteDuration);
        }

    }
}