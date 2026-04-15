package com.atymelancholy.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.service.ArraySortingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultArraySortingServiceTest {

    private static final int[] sourceValues = {5, -1, 3, 3, 0};

    private ArraySortingService service;

    @BeforeEach
    void setUp() {
        service = new DefaultArraySortingService();
    }

    @Test
    void sortByBubbleSort_returnsSortedCopyWithoutMutatingSource() {
        IntegerSequence sequence = new IntegerSequence(1L, "sample", sourceValues);
        int[] sorted = service.sortByBubbleSort(sequence);
        assertArrayEquals(new int[] {-1, 0, 3, 3, 5}, sorted);
        assertArrayEquals(sourceValues, sequence.getElements());
    }

    @Test
    void sortByInsertionSort_returnsSortedCopyWithoutMutatingSource() {
        IntegerSequence sequence = new IntegerSequence(2L, "sample-2", sourceValues);
        int[] sorted = service.sortByInsertionSort(sequence);
        assertArrayEquals(new int[] {-1, 0, 3, 3, 5}, sorted);
        assertArrayEquals(sourceValues, sequence.getElements());
    }
}
