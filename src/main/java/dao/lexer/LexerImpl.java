package dao.lexer;
import exception.*;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperience;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;
import translation.part.Part;

import java.util.*;

public class LexerImpl implements Lexer {
    @Override
    public Broadcaster interpret(String inputString) {
        return switch (inputString.charAt(0)) {
            case 'R' -> interpretRadioBroadcaster(inputString.substring(2).strip());
            case 'G' -> interpretGuestBroadcaster(inputString.substring(2).strip());
            default -> throw new UnknownBroadcasterException(inputString.charAt(0));
        };
    }

    private GuestBroadcaster interpretGuestBroadcaster(String stringValue) {
        var name = stringValue.substring(0, stringValue.indexOf(", "));
        var resume = stringValue.substring(stringValue.indexOf("(") + 1, stringValue.indexOf(")")).strip();
        var translations = interpretTranslations(stringValue.substring(stringValue.indexOf(")") +2).strip());
        return new GuestBroadcaster(name, resume, translations);
    }

    private RadioBroadcaster interpretRadioBroadcaster(String stringValue) {
        var name = stringValue.substring(0, stringValue.indexOf(", "));
        var experiences = interpretExperiences(stringValue.substring(stringValue.indexOf("(") + 1, stringValue.indexOf(")")));
        var translations = interpretTranslations(stringValue.substring(stringValue.indexOf(")") + 2).strip());
        return new RadioBroadcaster(name, experiences, translations);
    }

    private LinkedHashSet<Translation> interpretTranslations(String substring) {
        var translations = new LinkedHashSet<Translation>();
        if(substring.equals("")) return translations;
        var translationsString = substring.split("\\|");
        for (var t : translationsString) translations.add(interpretTranslation(t));
        return translations;
    }

    private Translation interpretTranslation(String translationString) {
        if(translationString.charAt(0) != 'T') throw new ThereMustBeTranslationException(translationString);
        var elems = translationString.split(" ");
        var price = convertToDouble(elems[1]);
        var duration = convertToDouble(elems[2]);
        var parts = interpretParts(translationString.substring(translationString.indexOf("[") +1, translationString.length() -1));
        return new TranslationImpl(price, duration, parts);
    }

    private Deque<Part> interpretParts(String substring) {
        var partsArr = substring.split("=>");
        var parts = new ArrayDeque<Part>();
        if(substring.equals("")) return parts;
        for (var p : partsArr) {
            parts.add(switch (p.charAt(0)) {
                case 'M' -> interpretMusic(p.split(", "));
                case 'I' -> interpretInterview(p.split(", "));
                case 'A' -> interpretAdvertising(p.split(", "));
                default -> throw new UnknownPartTypeException(p.charAt(0));
            });
        }
        return parts;
    }

    private Part interpretAdvertising(String[] elems) {
        if(elems.length != 3) throw new CantReadAdvertisingException(Arrays.toString(elems));
        return new Advertisement(elems[1], convertToDouble(elems[2]));
    }

    private Part interpretInterview(String[] elems) {
        if(elems.length != 3) throw  new CantReadInterviewException(Arrays.toString(elems));
        return new Interview(elems[1], convertToDouble(elems[2]));
    }

    private Part interpretMusic(String[] elems) {
        if(elems.length != 4) throw new CantReadMusicException(Arrays.toString(elems));
        return new Music(elems[1], elems[2], convertToDouble(elems[3]));
    }

    private LinkedHashSet<WorkOnRadioExperience> interpretExperiences(String substring) {
        var experiencesSet = new LinkedHashSet<WorkOnRadioExperience>();
        if(substring.equals("")) return experiencesSet;
        var expArr = substring.split("=>");
        for (var experience : expArr) {
            var elem = experience.split(", ");
            if(elem.length != 3 && !elem[0].equals("E")) throw new CantReadExperienceException(experience);
            experiencesSet.add(new WorkOnRadioExperience(elem[1].strip(), convertToDouble(elem[2].strip())));
        }
        return experiencesSet;
    }


    private double convertToDouble(String doubleString) {
        return Double.parseDouble(doubleString);
    }

}
