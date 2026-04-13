package com.javakick.arrays.parsing;

import com.javakick.arrays.exception.InvalidSequenceDefinitionException;
import com.javakick.arrays.validation.ArrayDefinitionValidator;

public final class DefaultIntegerSequenceLineParser implements IntegerSequenceLineParser {

    private final SequenceLineTokenizer tokenizer = new SequenceLineTokenizer();
    private final ArrayDefinitionValidator validator;

    public DefaultIntegerSequenceLineParser(ArrayDefinitionValidator validator) {
        this.validator = validator;
    }

    @Override
    public int[] parseToPrimitiveArray(String line) throws InvalidSequenceDefinitionException {
        String trimmed = line.trim();
        if (!validator.isValid(trimmed)) {
            throw new InvalidSequenceDefinitionException("Line failed validation: " + trimmed);
        }
        return parseValidatedLine(trimmed);
    }

    private int[] parseValidatedLine(String trimmed) throws InvalidSequenceDefinitionException {
        String[] tokens = tokenizer.extractNonEmptyTokens(trimmed);
        int length = tokens.length;
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            String token = tokens[i];
            try {
                values[i] = Integer.parseInt(token);
            } catch (NumberFormatException ex) {
                throw new InvalidSequenceDefinitionException("Cannot parse token: " + token, ex);
            }
        }
        return values;
    }
}
