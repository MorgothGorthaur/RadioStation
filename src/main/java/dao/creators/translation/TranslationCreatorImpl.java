package dao.creators.translation;

import dao.creators.translation.part.AdvertisementCreator;
import dao.creators.translation.part.InterviewCreator;
import dao.creators.translation.part.MusicCreator;
import dao.creators.translation.part.PartCreatorFactory;
import exception.AllTranslationTimeIsUsedException;
import exception.TooBigCommercialTimeException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import translation.Translation;
import translation.TranslationImpl;

import java.io.BufferedReader;


public class TranslationCreatorImpl implements TranslationCreator{
    private final PartCreatorFactory factory;
    private final BufferedReader reader;
    public TranslationCreatorImpl(BufferedReader reader){
        factory = new PartCreatorFactory(reader);
        this.reader = reader;
    }

    @Override
    @SneakyThrows
    public Translation create() {
        try {
            System.out.println("print translation time (in minutes) ");
            var builder = new TranslationImpl.Builder(Double.parseDouble(reader.readLine()));
            var line = "";
            while (!(line = reader.readLine()).equals("build")) translationMenuHandler(factory, builder, line);
            return builder.build();
        } catch (TooBigCommercialTimeException | AllTranslationTimeIsUsedException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return create();
        }
    }

    private void translationMenuHandler(PartCreatorFactory factory, TranslationImpl.Builder builder, String line) {
        switch (line) {
            case "add music" -> builder.addPart(factory.createPart(PartCreatorFactory.PartType.MUSIC));
            case "add interview" -> builder.addPart(factory.createPart(PartCreatorFactory.PartType.INTERVIEW));
            case "add advertisement" -> builder.addPart(factory.createPart(PartCreatorFactory.PartType.ADVERTISEMENT));
            case "build" -> System.out.println("built");
            default -> printTranslationMenu();
        }
    }

    private void printTranslationMenu() {
        System.out.println("""
                Translation menu
                """);
    }
}
