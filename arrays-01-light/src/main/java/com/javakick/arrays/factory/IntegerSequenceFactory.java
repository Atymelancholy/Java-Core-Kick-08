package com.javakick.arrays.factory;

import com.javakick.arrays.entity.IntegerSequence;

public interface IntegerSequenceFactory {

    IntegerSequence create(int[] values);
}
