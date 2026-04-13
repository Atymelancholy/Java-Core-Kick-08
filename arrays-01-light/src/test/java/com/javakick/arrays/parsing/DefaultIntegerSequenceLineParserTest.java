package com.javakick.arrays.parsing;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.javakick.arrays.exception.InvalidSequenceDefinitionException;
import com.javakick.arrays.validation.ArrayDefinitionValidator;
import com.javakick.arrays.validation.DefaultArrayDefinitionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultIntegerSequenceLineParserTest {

    private static final int[] EXPECTED_ONE_TWO_THREE = {1, 2, 3};

    private IntegerSequenceLineParser parser;

    @BeforeEach
    void setUp() {
        ArrayDefinitionValidator validator = new DefaultArrayDefinitionValidator();
        parser = new DefaultIntegerSequenceLineParser(validator);
    }

    @Test
    void parsesCommaSeparatedValues() throws InvalidSequenceDefinitionException {
        String line = "1, 2, 3";
        int[] result = parser.parseToPrimitiveArray(line);
        assertArrayEquals(EXPECTED_ONE_TWO_THREE, result);
    }

    @Test
    void parsesEmptyLineToEmptyArray() throws InvalidSequenceDefinitionException {
        String line = "";
        int[] result = parser.parseToPrimitiveArray(line);
        assertArrayEquals(new int[0], result);
    }

    @Test
    void rejectsInvalidLine() {
        String line = "1, x3, 4";
        InvalidSequenceDefinitionException thrown =
                assertThrows(InvalidSequenceDefinitionException.class, () -> parser.parseToPrimitiveArray(line));
        assertNotNull(thrown.getMessage());
    }
}
