package com.javakick.arrays.service.impl;

import com.javakick.arrays.entity.AbstractIntegerSequence;
import com.javakick.arrays.service.MinimumMaximumService;
import java.util.Optional;
import java.util.function.IntBinaryOperator;

public final class DefaultMinimumMaximumService implements MinimumMaximumService {

    @Override
    public Optional<Integer> findMinimum(AbstractIntegerSequence sequence) {
        return extremum(sequence, (left, right) -> {
            if (left < right) {
                return left;
            }
            return right;
        });
    }

    @Override
    public Optional<Integer> findMaximum(AbstractIntegerSequence sequence) {
        return extremum(sequence, (left, right) -> {
            if (left > right) {
                return left;
            }
            return right;
        });
    }

    private Optional<Integer> extremum(AbstractIntegerSequence sequence, IntBinaryOperator pickBetter) {
        int length = sequence.getLength();
        if (length == 0) {
            return Optional.empty();
        }
        int best = sequence.getElement(0);
        for (int i = 1; i < length; i++) {
            int current = sequence.getElement(i);
            best = pickBetter.applyAsInt(best, current);
        }
        return Optional.of(best);
    }
}
