package com.atymelancholy.arrays.repository.specification;

import com.atymelancholy.arrays.entity.IntegerSequence;

public final class NameEqualsSpecification implements Specification<IntegerSequence> {

    private final String expectedName;

    public NameEqualsSpecification(String expectedName) {
        this.expectedName = expectedName;
    }

    @Override
    public boolean isSatisfiedBy(IntegerSequence value) {
        return value.getName().equals(expectedName);
    }
}
