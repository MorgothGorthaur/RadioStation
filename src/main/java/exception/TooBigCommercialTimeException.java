package exception;

public class TooBigCommercialTimeException extends RuntimeException {
    public TooBigCommercialTimeException(double commercialTime, double minuteDuration) {
        super("The commercial time must be not bigger then 50%, but you have: " + Math.round((commercialTime * 100 / minuteDuration) * 100) / 100 + "%");
    }
}
