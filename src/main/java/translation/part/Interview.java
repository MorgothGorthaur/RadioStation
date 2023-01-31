package translation.part;

import exception.InterviewCreationException;
import lombok.NonNull;

public record Interview(@NonNull String interviewee,@NonNull double minuteDuration) implements CommercialPart {

    public Interview{
        if(interviewee.equals("") || minuteDuration <= 0) throw new InterviewCreationException();
    }
    @Override
    public double getPrice() {
        return 30 * minuteDuration;
    }

    @Override
    public String toString() {
        return "I, " + interviewee + ", " + minuteDuration;
    }
}
