package translation.part;

import exception.InterviewCreationException;
import lombok.NonNull;

import java.io.Serializable;

public record Interview(@NonNull String interviewee, double minuteDuration, double price) implements CommercialPart {

    public Interview{
        if(interviewee.equals("") || minuteDuration <= 0) throw new InterviewCreationException();
    }
    public Interview(String interviewee, double minuteDuration) {
        this(interviewee, minuteDuration, 30 * minuteDuration);
    }

}
