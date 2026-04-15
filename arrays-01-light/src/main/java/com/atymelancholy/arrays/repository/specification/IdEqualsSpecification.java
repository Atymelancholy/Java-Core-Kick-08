package com.atymelancholy.arrays.repository.specification;

import com.atymelancholy.arrays.entity.IntegerSequence;

public final class IdEqualsSpecification implements Specification<IntegerSequence> {

    private final long expectedId;

    public IdEqualsSpecification(long expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public boolean isSatisfiedBy(IntegerSequence value) {
        return value.getId() == expectedId;
    }
}
