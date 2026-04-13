package com.javakick.arrays.service;

import com.javakick.arrays.entity.AbstractIntegerSequence;
import java.util.Optional;

public interface MinimumMaximumService {

    Optional<Integer> findMinimum(AbstractIntegerSequence sequence);

    Optional<Integer> findMaximum(AbstractIntegerSequence sequence);
}
