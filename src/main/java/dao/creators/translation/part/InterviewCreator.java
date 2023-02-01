package dao.creators.translation.part;

import exception.InterviewCreationException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import translation.part.Interview;

import java.io.BufferedReader;

@AllArgsConstructor
public class InterviewCreator implements PartCreator {
    private final BufferedReader reader;

    @Override
    @SneakyThrows
    public Interview create() {
        try {
            System.out.print("print interviewee name ");
            var interviewee = reader.readLine();
            System.out.print("print duration (in minute) ");
            var duration = Double.parseDouble(reader.readLine());
            return new Interview(interviewee, duration);
        } catch (InterviewCreationException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return create();
        }
    }
}
