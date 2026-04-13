package com.javakick.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.javakick.arrays.entity.IntegerSequence;
import com.javakick.arrays.service.AverageService;
import com.javakick.arrays.service.SummationService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultAverageServiceTest {

    private static final int[] SAMPLE = {2, 4, 6};

    private AverageService service;

    @BeforeEach
    void setUp() {
        SummationService summationService = new DefaultSummationService();
        service = new DefaultAverageService(summationService);
    }

    @Test
    void computesAverage() {
        IntegerSequence sequence = new IntegerSequence(SAMPLE);
        Optional<Double> average = service.average(sequence);
        assertEquals(Optional.of(4.0), average);
    }
}
