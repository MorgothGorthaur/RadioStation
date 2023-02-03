package translation;

import exception.AllTranslationTimeIsUsedException;
import exception.TooBigCommercialTimeException;
import exception.TranslationCreationException;
import org.junit.jupiter.api.Test;
import translation.part.Advertisement;
import translation.part.Interview;
import translation.part.Music;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TranslationBuilderTest {

    @Test
    void testBuild() {
        var expected = new TranslationImpl(200, 38,
                new ArrayList<>(List.of(new Music("singer", "music", 10.5),
                        new Interview("person", 5),
                        new Advertisement("product", 10),
                        new Music("singer", "music", 12.5))));
        var result = new TranslationImpl.Builder(38).addPart(new Music("singer", "music", 10.5))
                .addPart(new Interview("person", 5))
                .addPart(new Advertisement("product", 10))
                .addPart(new Music("singer", "music", 12.5)).build();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testBuild_shouldThrownTranslationCreationException() {
        assertThatThrownBy(()-> new TranslationImpl.Builder(-3))
                .isInstanceOf(TranslationCreationException.class);
    }

    @Test
    void testBuild_shouldThrowAllTranslationTimeIsUsedException() {
        assertThatThrownBy(() -> new TranslationImpl.Builder(1).addPart(new Music("f", "f", 5)))
                .isInstanceOf(AllTranslationTimeIsUsedException.class);
    }

    @Test
    void testBuild_shouldThrowTooBigCommercialTimeException() {
        assertThatThrownBy(() -> new TranslationImpl.Builder(4).addPart(new Interview("f", 3)))
                .isInstanceOf(TooBigCommercialTimeException.class);
    }
}
