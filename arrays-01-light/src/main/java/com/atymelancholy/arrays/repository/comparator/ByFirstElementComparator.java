package com.atymelancholy.arrays.repository.comparator;

import com.atymelancholy.arrays.entity.IntegerSequence;
import java.util.Comparator;

public final class ByFirstElementComparator implements Comparator<IntegerSequence> {

    @Override
    public int compare(IntegerSequence left, IntegerSequence right) {
        int leftValue = left.getLength() == 0 ? 0 : left.getElement(0);
        int rightValue = right.getLength() == 0 ? 0 : right.getElement(0);
        return Integer.compare(leftValue, rightValue);
    }
}
