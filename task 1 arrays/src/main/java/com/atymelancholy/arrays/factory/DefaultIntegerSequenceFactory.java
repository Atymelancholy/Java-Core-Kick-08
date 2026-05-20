package com.atymelancholy.arrays.factory;

import com.atymelancholy.arrays.entity.IntegerSequence;

public final class DefaultIntegerSequenceFactory implements IntegerSequenceFactory {

    @Override
    public IntegerSequence create(long id, String name, int[] values) {
        return new IntegerSequence(id, name, values);
    }
}
