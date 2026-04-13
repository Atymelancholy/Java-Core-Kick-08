package com.javakick.arrays.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultArrayDefinitionValidatorTest {

    private static final String VALID_COMMA = "1, 2, 3";
    private static final String VALID_DASHES = "1 – 2 - 3";
    private static final String VALID_SPACES = "3 4 7";
    private static final String INVALID_LETTER = "1y1 21 32";
    private static final String INVALID_DOTS = "1, 2, x3, 6..5, 77";

    private ArrayDefinitionValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DefaultArrayDefinitionValidator();
    }

    @Test
    void acceptsCommaSeparatedLine() {
        String line = VALID_COMMA;
        boolean valid = validator.isValid(line);
        assertTrue(valid);
    }

    @Test
    void acceptsDashSeparatedLine() {
        String line = VALID_DASHES;
        boolean valid = validator.isValid(line);
        assertTrue(valid);
    }

    @Test
    void acceptsWhitespaceSeparatedLine() {
        String line = VALID_SPACES;
        boolean valid = validator.isValid(line);
        assertTrue(valid);
    }

    @Test
    void acceptsBlankLine() {
        String line = "   ";
        boolean valid = validator.isValid(line);
        assertTrue(valid);
    }

    @Test
    void rejectsLineWithLettersInsideToken() {
        String line = INVALID_LETTER;
        boolean valid = validator.isValid(line);
        assertFalse(valid);
    }

    @Test
    void rejectsLineWithMalformedNumber() {
        String line = INVALID_DOTS;
        boolean valid = validator.isValid(line);
        assertFalse(valid);
    }
}
