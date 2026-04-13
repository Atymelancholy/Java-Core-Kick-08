package com.javakick.arrays.factory;

import com.javakick.arrays.entity.IntegerSequence;

public final class DefaultIntegerSequenceFactory implements IntegerSequenceFactory {

    @Override
    public IntegerSequence create(int[] values) {
        return new IntegerSequence(values);
    }
}
