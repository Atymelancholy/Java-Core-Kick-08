package com.atymelancholy.arrays.repository.specification;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.warehouse.SequenceStatistics;
import com.atymelancholy.arrays.warehouse.Warehouse;
import java.util.Optional;
import java.util.function.ToDoubleFunction;

public final class WarehouseMetricSpecification implements Specification<IntegerSequence> {

    private final Warehouse warehouse;
    private final ToDoubleFunction<SequenceStatistics> metricReader;
    private final double expected;
    private final ComparisonOperator operator;

    public WarehouseMetricSpecification(
            Warehouse warehouse,
            ToDoubleFunction<SequenceStatistics> metricReader,
            double expected,
            ComparisonOperator operator) {
        this.warehouse = warehouse;
        this.metricReader = metricReader;
        this.expected = expected;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfiedBy(IntegerSequence value) {
        Optional<SequenceStatistics> stats = warehouse.get(value.getId());
        if (stats.isEmpty()) {
            return false;
        }
        double current = metricReader.applyAsDouble(stats.get());
        return LengthSpecification.compare(current, expected, operator);
    }
}
