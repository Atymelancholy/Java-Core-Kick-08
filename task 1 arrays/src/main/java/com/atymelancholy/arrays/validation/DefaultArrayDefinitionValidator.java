package com.atymelancholy.arrays.validation;

import com.atymelancholy.arrays.format.FormatRules;
import com.atymelancholy.arrays.parsing.SequenceLineTokenizer;

public final class DefaultArrayDefinitionValidator implements ArrayDefinitionValidator {

    private final SequenceLineTokenizer tokenizer = new SequenceLineTokenizer();

    @Override
    public boolean isValid(String line) {
        String stripped = line.strip();
        if (stripped.isEmpty()) {
            return true;
        }
        String[] tokens = tokenizer.extractNonEmptyTokens(stripped);
        for (String token : tokens) {
            if (!FormatRules.INTEGER_TOKEN.matcher(token).matches()) {
                return false;
            }
        }
        return true;
    }
}
