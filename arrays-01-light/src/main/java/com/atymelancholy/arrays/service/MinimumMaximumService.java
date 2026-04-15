package com.atymelancholy.arrays.service;

import com.atymelancholy.arrays.entity.AbstractIntegerSequence;
import java.util.Optional;

public interface MinimumMaximumService {

    Optional<Integer> findMinimum(AbstractIntegerSequence sequence);
    Optional<Integer> findMaximum(AbstractIntegerSequence sequence);
}
