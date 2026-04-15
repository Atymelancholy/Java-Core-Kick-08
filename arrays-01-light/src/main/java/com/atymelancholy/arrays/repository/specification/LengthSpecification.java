package com.atymelancholy.arrays.repository.specification;

import com.atymelancholy.arrays.entity.IntegerSequence;

public final class LengthSpecification implements Specification<IntegerSequence> {

    private final int expected;
    private final ComparisonOperator operator;

    public LengthSpecification(int expected, ComparisonOperator operator) {
        this.expected = expected;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfiedBy(IntegerSequence value) {
        int current = value.getLength();
        return compare(current, expected, operator);
    }

    static boolean compare(double left, double right, ComparisonOperator operator) {
        if (operator == ComparisonOperator.GREATER_THAN) {
            return left > right;
        }
        if (operator == ComparisonOperator.LESS_THAN) {
            return left < right;
        }
        return Double.compare(left, right) == 0;
    }
}
