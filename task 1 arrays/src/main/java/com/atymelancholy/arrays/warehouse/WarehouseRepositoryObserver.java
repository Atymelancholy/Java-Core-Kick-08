package com.atymelancholy.arrays.warehouse;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.repository.observer.RepositoryObserver;

public final class WarehouseRepositoryObserver implements RepositoryObserver<IntegerSequence> {

    private final Warehouse warehouse;
    private final SequenceStatisticsCalculator calculator;

    public WarehouseRepositoryObserver(Warehouse warehouse, SequenceStatisticsCalculator calculator) {
        this.warehouse = warehouse;
        this.calculator = calculator;
    }

    @Override
    public void onAdd(IntegerSequence value) {
        warehouse.put(value.getId(), calculator.calculate(value));
    }

    @Override
    public void onUpdate(IntegerSequence value) {
        warehouse.put(value.getId(), calculator.calculate(value));
    }

    @Override
    public void onRemove(IntegerSequence value) {
        warehouse.remove(value.getId());
    }
}
