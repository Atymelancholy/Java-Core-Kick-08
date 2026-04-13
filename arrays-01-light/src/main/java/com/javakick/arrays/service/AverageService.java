package com.javakick.arrays.service;

import com.javakick.arrays.entity.AbstractIntegerSequence;
import java.util.Optional;

public interface AverageService {

    Optional<Double> average(AbstractIntegerSequence sequence);
}
