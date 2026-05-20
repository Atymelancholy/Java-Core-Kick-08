package com.atymelancholy.arrays.repository.comparator;

import com.atymelancholy.arrays.entity.IntegerSequence;
import java.util.Comparator;

public final class ByNameComparator implements Comparator<IntegerSequence> {

    @Override
    public int compare(IntegerSequence left, IntegerSequence right) {
        return left.getName().compareTo(right.getName());
    }
}
