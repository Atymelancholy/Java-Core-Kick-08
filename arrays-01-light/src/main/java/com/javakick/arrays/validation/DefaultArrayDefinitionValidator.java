package com.javakick.arrays.validation;

import com.javakick.arrays.format.FormatRules;
import com.javakick.arrays.parsing.SequenceLineTokenizer;

public final class DefaultArrayDefinitionValidator implements ArrayDefinitionValidator {

    private final SequenceLineTokenizer tokenizer = new SequenceLineTokenizer();

    @Override
    public boolean isValid(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return true;
        }
        String[] tokens = tokenizer.extractNonEmptyTokens(trimmed);
        for (String token : tokens) {
            if (!FormatRules.INTEGER_TOKEN.matcher(token).matches()) {
                return false;
            }
        }
        return true;
    }
}
