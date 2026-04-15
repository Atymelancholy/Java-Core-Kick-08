package com.atymelancholy.arrays.parsing;

import com.atymelancholy.arrays.exception.InvalidSequenceDefinitionException;

public interface IntegerSequenceLineParser {

    int[] parseToPrimitiveArray(String line) throws InvalidSequenceDefinitionException;
}
