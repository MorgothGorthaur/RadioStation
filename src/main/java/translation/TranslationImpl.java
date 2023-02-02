package translation;

import exception.AllTranslationTimeIsUsedException;
import exception.TooBigCommercialTimeException;
import exception.TranslationCreationException;
import lombok.Getter;
import translation.part.CommercialPart;
import translation.part.Part;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public record TranslationImpl(double price, double minuteDuration, List<Part> parts) implements Translation {
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
        private final Deque<Part> parts = new ArrayDeque<>();

        public Builder(double minuteDuration) {
            if (minuteDuration <= 0) throw new TranslationCreationException();
            this.minuteDuration = minuteDuration;
            this.freeTime = minuteDuration;
        }

        public Builder addPart(Part part) {
            if (freeTime - part.minuteDuration() < 0) throw new AllTranslationTimeIsUsedException();
            freeTime -= part.minuteDuration();
            if (part instanceof CommercialPart commercialPart) {
                price += commercialPart.getPrice();
                if (commercialTime > minuteDuration / 2) throw new TooBigCommercialTimeException(commercialTime, minuteDuration);
                commercialTime += commercialPart.minuteDuration();
            }
            parts.add(part);
            return this;
        }


        public Builder addParts(Deque<Part> parts) {
            for (var part : parts) addPart(part);
            return this;
        }

        public Translation build() {
            return new TranslationImpl(price, minuteDuration, parts);
        }

    }
}