import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SubstringTests {
    @Test
    public void testPositionAsterikInPattern_validArgument_success() {
        final String original = "str*ing";
        final int expected = 3;

        final int result = Substring.positionAsterikInPattern(original);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testPositionAsterikInPattern_nullArgument_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () ->
            Substring.positionAsterikInPattern(null));
    }

    @ParameterizedTest
    @ValueSource (strings = {"*c", "b*b", "er*", "dfdd" })
    public void testPositionAsteriskInPattern_Not_Exception(String argument) {
        Executable executable = () -> Substring.positionAsterikInPattern(argument);
        Assertions.assertDoesNotThrow(executable);
    }
}