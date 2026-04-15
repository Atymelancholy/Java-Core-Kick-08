package com.atymelancholy.arrays.technical;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.repository.IntegerSequenceRepository;
import com.atymelancholy.arrays.repository.comparator.ByFirstElementComparator;
import com.atymelancholy.arrays.repository.comparator.ByIdComparator;
import com.atymelancholy.arrays.repository.comparator.ByLengthComparator;
import com.atymelancholy.arrays.repository.comparator.ByNameComparator;
import com.atymelancholy.arrays.repository.specification.ComparisonOperator;
import com.atymelancholy.arrays.repository.specification.IdEqualsSpecification;
import com.atymelancholy.arrays.repository.specification.LengthSpecification;
import com.atymelancholy.arrays.repository.specification.NameEqualsSpecification;
import com.atymelancholy.arrays.repository.specification.WarehouseMetricSpecification;
import com.atymelancholy.arrays.service.AverageService;
import com.atymelancholy.arrays.service.MinimumMaximumService;
import com.atymelancholy.arrays.service.SummationService;
import com.atymelancholy.arrays.service.impl.DefaultAverageService;
import com.atymelancholy.arrays.service.impl.DefaultMinimumMaximumService;
import com.atymelancholy.arrays.service.impl.DefaultSummationService;
import com.atymelancholy.arrays.support.SingletonResetSupport;
import com.atymelancholy.arrays.warehouse.SequenceStatistics;
import com.atymelancholy.arrays.warehouse.SequenceStatisticsCalculator;
import com.atymelancholy.arrays.warehouse.Warehouse;
import com.atymelancholy.arrays.warehouse.WarehouseRepositoryObserver;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskPartTwoRequirementsTest {

    private IntegerSequenceRepository repository;
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        SingletonResetSupport.resetAll();

        MinimumMaximumService minMax = new DefaultMinimumMaximumService();
        SummationService sum = new DefaultSummationService();
        AverageService avg = new DefaultAverageService(sum);

        repository = IntegerSequenceRepository.getInstance();
        warehouse = Warehouse.getInstance();
        SequenceStatisticsCalculator calculator = new SequenceStatisticsCalculator(minMax, sum, avg);
        repository.addObserver(new WarehouseRepositoryObserver(warehouse, calculator));
    }

    @AfterEach
    void tearDown() {
        SingletonResetSupport.resetAll();
    }

    @Test
    void getInstance_returnsSameRepositoryAndWarehouseSingletons() {
        assertSame(repository, IntegerSequenceRepository.getInstance());
        assertSame(warehouse, Warehouse.getInstance());
    }

    @Test
    void query_sort_updateElement_removeById_followTaskPartTwoRequirements() {
        IntegerSequence alpha = new IntegerSequence(10L, "alpha", new int[] {3, 1, 2});
        IntegerSequence beta = new IntegerSequence(20L, "beta", new int[] {9});
        IntegerSequence gamma = new IntegerSequence(30L, "gamma", new int[] {});

        repository.add(alpha);
        repository.add(beta);
        repository.add(gamma);

        assertEquals(alpha, repository.findById(10L).orElseThrow());
        assertEquals(beta, repository.findByName("beta").orElseThrow());

        List<IntegerSequence> idSelection = repository.query(new IdEqualsSpecification(20L));
        List<IntegerSequence> nameSelection = repository.query(new NameEqualsSpecification("gamma"));
        assertEquals(List.of(beta), idSelection);
        assertEquals(List.of(gamma), nameSelection);

        List<IntegerSequence> longEnough = repository.query(
                new LengthSpecification(1, ComparisonOperator.GREATER_THAN));
        assertEquals(List.of(alpha), longEnough);

        List<IntegerSequence> bigSum = repository.query(
                new WarehouseMetricSpecification(
                        warehouse,
                        stats -> stats.getSum(),
                        5,
                        ComparisonOperator.GREATER_THAN));
        assertEquals(List.of(alpha, beta), bigSum);

        List<IntegerSequence> byId = repository.sort(new ByIdComparator());
        List<IntegerSequence> byName = repository.sort(new ByNameComparator());
        List<IntegerSequence> byLength = repository.sort(new ByLengthComparator());
        List<IntegerSequence> byFirstElement = repository.sort(new ByFirstElementComparator());

        assertEquals(List.of(alpha, beta, gamma), byId);
        assertEquals(List.of(alpha, beta, gamma), byName);
        assertEquals(List.of(gamma, beta, alpha), byLength);
        assertEquals(List.of(gamma, alpha, beta), byFirstElement);

        assertTrue(repository.updateElement(10L, 0, 100));
        SequenceStatistics recalculated = warehouse.get(10L).orElseThrow();
        assertEquals(103L, recalculated.getSum());
        assertEquals(1, recalculated.getMin());
        assertEquals(100, recalculated.getMax());

        assertTrue(repository.removeById(20L));
        assertTrue(warehouse.get(20L).isEmpty());
    }
}
