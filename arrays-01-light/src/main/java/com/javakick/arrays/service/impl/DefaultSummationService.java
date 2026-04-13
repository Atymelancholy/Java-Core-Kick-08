package com.javakick.arrays.service.impl;

import com.javakick.arrays.entity.AbstractIntegerSequence;
import com.javakick.arrays.service.SummationService;
import java.util.Optional;

public final class DefaultSummationService implements SummationService {

    @Override
    public Optional<Long> sum(AbstractIntegerSequence sequence) {
        int length = sequence.getLength();
        if (length == 0) {
            return Optional.empty();
        }
        long total = 0L;
        for (int i = 0; i < length; i++) {
            total = total + sequence.getElement(i);
        }
        return Optional.of(total);
    }
}
