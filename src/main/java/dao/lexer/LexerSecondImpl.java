package dao.lexer;

import lombok.SneakyThrows;
import personality.Broadcaster;
import personality.GuestBroadcaster;
import personality.RadioBroadcaster;
import personality.WorkOnRadioExperienceImpl;
import translation.Translation;
import translation.TranslationImpl;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;
import translation.part.Part;

import java.util.*;

public class LexerSecondImpl implements Lexer {
    @Override
    public Broadcaster interpret(String inputString) {
        var input = new StringBuilder(inputString);
        var type = new StringBuilder();
        while (input.length() != 0) {
            type.append(input.charAt(0));
            input.delete(0, 1);
            if (type.toString().equals("RadioBroadcaster")) return interpretRadioBroadcaster(input);
            if (type.toString().equals("GuestBroadcaster")) return interpretGuestBroadcaster(input);
        }
        throw new RuntimeException();
    }

    private GuestBroadcaster interpretGuestBroadcaster(StringBuilder input) {
        var flag = new StringBuilder();
        var name = "";
        var resume = "";
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("(name=")) {
                name = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" resume=") && !name.equals("")) {
                resume = interpretName(input);
                flag = new StringBuilder();
            }
            if(flag.toString().equals(" translations=[")){
                if (input.charAt(0) != ']') return new GuestBroadcaster(name, resume, new LinkedHashSet<>(interpretTranslations(input)));
                return new GuestBroadcaster(name, resume, new LinkedHashSet<>());
            }
        }
        throw new RuntimeException();
    }

    private RadioBroadcaster interpretRadioBroadcaster(StringBuilder input) {
        var flag = new StringBuilder();
        var name = " ";
        LinkedHashSet<WorkOnRadioExperienceImpl> experiences = null;
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("(name=")) {
                name = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" experiences=[") && !name.equals("")) {
                if (input.charAt(0) != ']') experiences = new LinkedHashSet<>(interpretExperiences(input));
                else experiences = new LinkedHashSet<>();
                flag = new StringBuilder();
            }
            if (flag.toString().equals("], translations=[") && !name.equals("") && experiences != null) {
                if (input.charAt(0) != ']')
                    return new RadioBroadcaster(name, experiences, new LinkedHashSet<>(interpretTranslations(input)));
                return new RadioBroadcaster(name, experiences, new LinkedHashSet<>());
            }

        }
        throw new RuntimeException();
    }

    private LinkedHashSet<Translation> interpretTranslations(StringBuilder input) {
        var flag = new StringBuilder();
        var tr = new LinkedHashSet<Translation>();
        while (input.length() != 0 && input.charAt(0) != ']') {
            if (input.charAt(0) == ',' || input.charAt(0) == ' ') input.delete(0, 1);
            else tr.add(interpretTranslation(input));
        }
        return tr;
    }

    private Translation interpretTranslation(StringBuilder input) {
        var flag = new StringBuilder();
        var price = 0d;
        var duration = 0d;
        Deque<Part> parts;
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("TranslationImpl[price=")) {
                price = Double.parseDouble(interpretName(input));
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" minuteDuration=") && price != 0) {
                duration = Double.parseDouble(interpretName(input));
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" parts=[") && price != 0 && duration != 0) {
                if (input.charAt(0) != ']') parts = new ArrayDeque<>(interpretParts(input));
                else parts = new ArrayDeque<>();
                input.delete(0, 1);
                return new TranslationImpl(price, duration, parts);
            }

        }
        throw new RuntimeException();
    }

    private ArrayDeque<Part> interpretParts(StringBuilder input) {
        var flag = new StringBuilder();
        var parts = new ArrayDeque<Part>();
        while (input.length() != 0 && input.charAt(0) != ']') {
            var ch = input.charAt(0);
            if (ch != ' ' && ch != ',') flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("Music[")) {
                parts.add(interpretMusic(input));
                flag = new StringBuilder();
            }
            if (flag.toString().equals("Interview[")) {
                parts.add(interpretInterview(input));
                flag = new StringBuilder();
            }

            if (flag.toString().equals("Advertisement[")) {
                parts.add(interpretAdvertising(input));
                flag = new StringBuilder();
            }

        }
        input.delete(0, 1);
        return parts;
    }

    private Advertisement interpretAdvertising(StringBuilder input) {
        var productName = "";
        var duration = 0d;
        var flag = new StringBuilder();
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("productName=")) {
                productName = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" minuteDuration=")) {
                duration = Double.parseDouble(interpretName(input));
                return new Advertisement(productName, duration);
            }
        }
        throw new RuntimeException();
    }

    private Interview interpretInterview(StringBuilder input) {
        var flag = new StringBuilder();
        var interviewee = "";
        var duration = 0d;
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("interviewee=")) {
                interviewee = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" minuteDuration=")) {
                duration = Double.parseDouble(interpretName(input));
                return new Interview(interviewee, duration);
            }
        }
        throw new RuntimeException();
    }

    private Music interpretMusic(StringBuilder input) {
        var flag = new StringBuilder();
        var singerName = "";
        var musicName = "";
        var duration = 0d;
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("singerName=")) {
                singerName = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" musicName=")) {
                musicName = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" minuteDuration=")) {
                duration = Double.parseDouble(interpretName(input));
                return new Music(singerName, musicName, duration);
            }
        }
        throw new RuntimeException();
    }


    private LinkedHashSet<WorkOnRadioExperienceImpl> interpretExperiences(StringBuilder input) {
        var exp = new LinkedHashSet<WorkOnRadioExperienceImpl>();
        while (input.length() != 0 && input.charAt(0) != ']') {
            if (input.charAt(0) == ',' || input.charAt(0) == ' ') input.delete(0, 1);
            else exp.add(interpretExperience(input));
        }
        return exp;
    }

    private WorkOnRadioExperienceImpl interpretExperience(StringBuilder input) {
        var flag = new StringBuilder();
        var name = "";
        var years = 0d;
        while (input.length() != 0) {
            flag.append(input.charAt(0));
            input.delete(0, 1);
            if (flag.toString().equals("WorkOnRadioExperience[stationName=")) {
                name = interpretName(input);
                flag = new StringBuilder();
            }
            if (flag.toString().equals(" yearExperience=") && !name.equals("")) {
                years = Double.parseDouble(interpretName(input));
                return new WorkOnRadioExperienceImpl(name, years);
            }
        }
        throw new RuntimeException();
    }

    private String interpretName(StringBuilder input) {
        var name = new StringBuilder();
        while (input.length() != 0) {
            var ch = input.charAt(0);
            input.delete(0, 1);
            if (ch == ',' || ch == ']') return name.toString();
            else name.append(ch);
        }
        throw new RuntimeException();
    }

    @SneakyThrows
    public static void main(String[] args) {
        var br = new GuestBroadcaster("f", "F", new LinkedHashSet<>(List.of(new TranslationImpl(1,2, new ArrayDeque<Part>(List.of(new Music("g", "f", 4)))))));
        System.out.println(br);
        System.out.println(new LexerSecondImpl().interpret(br.toString()));
    }
}
