package translation.part;

import exception.InterviewCreationException;
import lombok.NonNull;

import java.io.Serializable;

public record Interview(@NonNull String interviewee, double minuteDuration) implements CommercialPart, Serializable {

    public Interview{
        if(interviewee.equals("") || minuteDuration <= 0) throw new InterviewCreationException();
    }
    @Override
    public double getPrice() {
        return 30 * minuteDuration;
    }

}
