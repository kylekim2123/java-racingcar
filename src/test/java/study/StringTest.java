package study;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringTest {

    @Test
    void splitStringsByComma() {
        final String input = "1,2";
        final String[] splitInput = input.split(",");

        assertThat(splitInput).containsExactly("1", "2");
    }

    @Test
    void splitSingleStringByComma() {
        final String input = "1";
        final String[] splitInput = input.split(",");

        assertThat(splitInput).containsExactly("1");
    }

    @Test
    void subString() {
        final String input = "(1,2)";
        final String subStringInput = input.substring(1, input.length() - 1);

        assertThat(subStringInput).isEqualTo("1,2");
    }
}