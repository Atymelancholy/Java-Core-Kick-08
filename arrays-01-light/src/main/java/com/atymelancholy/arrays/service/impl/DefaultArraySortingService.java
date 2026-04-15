package com.atymelancholy.arrays.service.impl;

import com.atymelancholy.arrays.entity.AbstractIntegerSequence;
import com.atymelancholy.arrays.service.ArraySortingService;

public final class DefaultArraySortingService implements ArraySortingService {

    @Override
    public int[] sortByBubbleSort(AbstractIntegerSequence sequence) {
        int[] values = sequence.getElements();
        bubbleSort(values);
        return values;
    }

    @Override
    public int[] sortByInsertionSort(AbstractIntegerSequence sequence) {
        int[] values = sequence.getElements();
        insertionSort(values);
        return values;
    }

    private void bubbleSort(int[] values) {
        for (int outer = 0; outer < values.length - 1; outer++) {
            for (int inner = 0; inner < values.length - 1 - outer; inner++) {
                if (values[inner] > values[inner + 1]) {
                    swap(values, inner, inner + 1);
                }
            }
        }
    }

    private void insertionSort(int[] values) {
        for (int i = 1; i < values.length; i++) {
            int current = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > current) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = current;
        }
    }

    private void swap(int[] values, int leftIndex, int rightIndex) {
        int temp = values[leftIndex];
        values[leftIndex] = values[rightIndex];
        values[rightIndex] = temp;
    }
}
