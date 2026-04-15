package com.atymelancholy.arrays.factory;

import com.atymelancholy.arrays.entity.IntegerSequence;

public interface IntegerSequenceFactory {

    IntegerSequence create(long id, String name, int[] values);
}
