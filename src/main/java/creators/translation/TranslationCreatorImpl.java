package creators.translation;

import creators.translation.part.PartCreatorFactory;
import exception.AllTranslationTimeIsUsedException;
import exception.TooBigCommercialTimeException;
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
            System.out.print("print translation time (in minutes) ");
            var builder = new TranslationImpl.Builder(Double.parseDouble(reader.readLine()));
            printTranslationMenu();
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
            default -> printTranslationMenu();
        }
    }

    private void printTranslationMenu() {
        System.out.println("""
                Translation menu
                add music - for adding music
                add interview - for adding interview
                add advertisement - for adding advertisement
                menu - reprints menu
                build - builds
                """);
    }
}
