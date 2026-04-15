package com.atymelancholy.arrays.entity;

public final class IntegerSequence extends AbstractIntegerSequence {

    public IntegerSequence(int[] source) {
        super(0L, "", source);
    }

    public IntegerSequence(long id, String name, int[] source) {
        super(id, name, source);
    }
}
