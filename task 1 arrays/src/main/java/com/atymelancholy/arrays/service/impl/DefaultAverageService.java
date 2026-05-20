package com.atymelancholy.arrays.service.impl;

import com.atymelancholy.arrays.entity.AbstractIntegerSequence;
import com.atymelancholy.arrays.service.AverageService;
import com.atymelancholy.arrays.service.SummationService;
import java.util.Optional;

public final class DefaultAverageService implements AverageService {

    private final SummationService summationService;

    public DefaultAverageService(SummationService summationService) {
        this.summationService = summationService;
    }

    @Override
    public Optional<Double> average(AbstractIntegerSequence sequence) {
        int length = sequence.getLength();
        if (length == 0) {
            return Optional.empty();
        }
        Optional<Long> total = summationService.sum(sequence);
        return total.map(sum -> sum.doubleValue() / length);
    }
}
