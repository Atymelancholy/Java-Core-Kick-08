package com.atymelancholy.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.service.SummationService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultSummationServiceTest {

    private static final int[] SAMPLE = {1, 2, 3};

    private SummationService service;

    @BeforeEach
    void setUp() {
        service = new DefaultSummationService();
    }

    @Test
    void sumsValues() {
        IntegerSequence sequence = new IntegerSequence(SAMPLE);
        Optional<Long> total = service.sum(sequence);
        assertEquals(Optional.of(6L), total);
    }
}
