package com.javakick.arrays.parsing;

import com.javakick.arrays.exception.InvalidSequenceDefinitionException;

public interface IntegerSequenceLineParser {

    int[] parseToPrimitiveArray(String line) throws InvalidSequenceDefinitionException;
}
