package translation.part;

public record Advertisement(String productName, double minuteDuration) implements CommercialPart {
    @Override
    public double getPrice() {
        return 5 * minuteDuration;
    }
}
