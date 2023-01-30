package translation.part;

public record Interview(String interviewee, double minuteDuration) implements CommercialPart {
    @Override
    public double getPrice() {
        return 30 * minuteDuration;
    }

    @Override
    public String toString() {
        return "I " + interviewee + " " + minuteDuration;
    }
}
