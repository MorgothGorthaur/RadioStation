package translation.part;

public record Music(String singerName, String musicName, double minuteDuration) implements Part {
    @Override
    public String toString() {
        return "M " + singerName + " " + musicName + " " + minuteDuration;
    }
}
