package com.atymelancholy.arrays.service;

import com.atymelancholy.arrays.entity.AbstractIntegerSequence;

public interface ArraySortingService {

    int[] sortByBubbleSort(AbstractIntegerSequence sequence);

    int[] sortByInsertionSort(AbstractIntegerSequence sequence);
}
