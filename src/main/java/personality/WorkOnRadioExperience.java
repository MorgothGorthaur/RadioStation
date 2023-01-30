package personality;

public record WorkOnRadioExperience(String stationName, double yearExperience) {
    @Override
    public String toString() {
        return "E " + stationName + " " + yearExperience;
    }
}
