package com.javakick.arrays.parsing;

import com.javakick.arrays.format.FormatRules;

public final class SequenceLineTokenizer {

    public String[] extractNonEmptyTokens(String trimmedLine) {
        if (trimmedLine.isEmpty()) {
            return new String[0];
        }
        String[] parts = FormatRules.ELEMENT_SEPARATOR.split(trimmedLine);
        int count = 0;
        for (String piece : parts) {
            if (!piece.isEmpty()) {
                count++;
            }
        }
        String[] result = new String[count];
        int index = 0;
        for (String piece : parts) {
            if (!piece.isEmpty()) {
                result[index] = piece;
                index++;
            }
        }
        return result;
    }
}
