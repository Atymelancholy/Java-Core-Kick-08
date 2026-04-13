package com.javakick.arrays.entity;

import java.util.Arrays;

public abstract class AbstractIntegerSequence {

    private final int[] storage;

    protected AbstractIntegerSequence(int[] source) {
        this.storage = Arrays.copyOf(source, source.length);
    }

    public final int getLength() {
        return storage.length;
    }

    public final int getElement(int index) {
        return storage[index];
    }
}
