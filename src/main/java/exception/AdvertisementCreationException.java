package exception;

public class AdvertisementCreationException extends RuntimeException {
    public AdvertisementCreationException() {
        super("product name must be not empty! duration time must be bigger then 0!");
    }
}
