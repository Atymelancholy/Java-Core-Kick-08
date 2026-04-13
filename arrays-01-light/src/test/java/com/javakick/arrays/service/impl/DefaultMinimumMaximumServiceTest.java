package com.javakick.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.javakick.arrays.entity.IntegerSequence;
import com.javakick.arrays.service.MinimumMaximumService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultMinimumMaximumServiceTest {

    private static final int[] SAMPLE = {4, 1, 9, 2};

    private MinimumMaximumService service;

    @BeforeEach
    void setUp() {
        service = new DefaultMinimumMaximumService();
    }

    @Test
    void findsMinimum() {
        IntegerSequence sequence = new IntegerSequence(SAMPLE);
        Optional<Integer> minimum = service.findMinimum(sequence);
        assertEquals(Optional.of(1), minimum);
    }

    @Test
    void findsMaximum() {
        IntegerSequence sequence = new IntegerSequence(SAMPLE);
        Optional<Integer> maximum = service.findMaximum(sequence);
        assertEquals(Optional.of(9), maximum);
    }
}
