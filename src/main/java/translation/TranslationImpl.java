package translation;

import exception.AllTranslationTimeIsUsedException;
import exception.TooBigCommercialTimeException;
import translation.part.CommercialPart;
import translation.part.Part;

import java.util.ArrayDeque;
import java.util.Deque;


public record TranslationImpl(double price, double minuteDuration, Deque<Part> parts) implements Translation {
    @Override
    public Deque<Part> parts() {
        return new ArrayDeque<>(parts);
    }

    @Override
    public String toString() {
        var str = new StringBuilder("T " + price + " " + minuteDuration + " [");
        for (var part : parts) str.append(part).append("=>");
        return str.append("]").toString();
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
            if (part instanceof CommercialPart commercialPart) {
                price += commercialPart.getPrice();
                commercialTime += commercialPart.minuteDuration();
            }
            checkConditions();
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

        private void checkConditions() {
            if (freeTime < 0) throw new AllTranslationTimeIsUsedException();
            if (commercialTime > minuteDuration / 2)
                throw new TooBigCommercialTimeException(commercialTime, minuteDuration);
        }
    }
}