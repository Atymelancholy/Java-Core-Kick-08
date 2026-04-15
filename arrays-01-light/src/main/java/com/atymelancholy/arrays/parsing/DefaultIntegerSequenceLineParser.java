package com.atymelancholy.arrays.parsing;

import com.atymelancholy.arrays.exception.InvalidSequenceDefinitionException;
import com.atymelancholy.arrays.validation.ArrayDefinitionValidator;

public final class DefaultIntegerSequenceLineParser implements IntegerSequenceLineParser {

    private final SequenceLineTokenizer tokenizer = new SequenceLineTokenizer();
    private final ArrayDefinitionValidator validator;

    public DefaultIntegerSequenceLineParser(ArrayDefinitionValidator validator) {
        this.validator = validator;
    }

    @Override
    public int[] parseToPrimitiveArray(String line) throws InvalidSequenceDefinitionException {
        String stripped = line.strip();
        if (!validator.isValid(stripped)) {
            throw new InvalidSequenceDefinitionException("Line failed validation: " + stripped);
        }
        return parseValidatedLine(stripped);
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
