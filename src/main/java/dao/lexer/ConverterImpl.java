package dao.lexer;

import exception.UnknownBroadcasterException;
import exception.UnknownPartTypeException;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;
import translation.Translation;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;
import translation.part.Part;

public class ConverterImpl implements Converter{
    @Override
    public String convert(Iterable<Broadcaster> broadcasters) {
        var str = new StringBuilder();
        for (var broadcaster : broadcasters) {
            if(broadcaster instanceof RadioBroadcaster radioBroadcaster) str.append(convertRadioBroadcaster(radioBroadcaster)).append("\n");
            else if(broadcaster instanceof GuestBroadcaster guestBroadcaster) str.append(convertGuestBroadcaster(guestBroadcaster)).append("\n");
            else throw new UnknownBroadcasterException(broadcaster.toString());
        }
        return str.toString();
    }

    private String convertGuestBroadcaster(GuestBroadcaster guestBroadcaster) {
        var str = new StringBuilder("G, " + guestBroadcaster.getName() + ", (" + guestBroadcaster.getResume() + "), ");
        for (var translation : guestBroadcaster.getTranslations()) str.append(convertTranslation(translation)).append("|");
        return str.toString();
    }

    private String convertRadioBroadcaster(RadioBroadcaster radioBroadcaster) {
        var str = new StringBuilder("R, " + radioBroadcaster.getName() + ", (");
        for (var exp : radioBroadcaster.getExperiences()) str.append(convertExperience(exp)).append("=>");
        str.append("), ");
        for (var translation : radioBroadcaster.getTranslations()) str.append(convertTranslation(translation)).append("|");
        return str.toString();
    }

    private String convertTranslation(Translation translation) {
        var str = new StringBuilder("T " + translation.price() + " " + translation.minuteDuration() + " [");
        for (var part : translation.parts()) str.append(convertPart(part)).append("=>");
        return str.append("]").toString();
    }

    private String convertPart(Part part) {
        if(part instanceof Music music) return "M, " + music.singerName() + ", " + music.musicName() + ", " + music.minuteDuration();
        else if(part instanceof Interview interview) return "I, " + interview.interviewee() + ", " + interview.minuteDuration();
        else if(part instanceof Advertisement advertisement) return "A, " + advertisement.productName() + ", " + advertisement.minuteDuration();
        else throw new UnknownPartTypeException(part.toString());
    }

    private String convertExperience(WorkOnRadioExperience exp) {
        return "E, " + exp.stationName() + ", " + exp.yearExperience();
    }
}
