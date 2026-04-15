package com.atymelancholy.arrays.service;

import com.atymelancholy.arrays.entity.AbstractIntegerSequence;
import java.util.Optional;

public interface SummationService {

    Optional<Long> sum(AbstractIntegerSequence sequence);
}
