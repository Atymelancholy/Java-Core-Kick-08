package com.atymelancholy.arrays.technical;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.atymelancholy.arrays.bootstrap.ApplicationComposition;
import com.atymelancholy.arrays.bootstrap.KickArraysApplication;
import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.repository.IntegerSequenceRepository;
import com.atymelancholy.arrays.support.SingletonResetSupport;
import com.atymelancholy.arrays.warehouse.SequenceStatistics;
import com.atymelancholy.arrays.warehouse.Warehouse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TaskPartOneRequirementsTest {

    @AfterEach
    void tearDown() {
        SingletonResetSupport.resetAll();
    }

    @Test
    void run_populatesRepositoryAndWarehouseForValidLines() throws IOException {
        SingletonResetSupport.resetAll();

        Path tempInputFile = Files.createTempFile("arrays-task-part1", ".txt");
        Files.write(tempInputFile, List.of("1,2,3", "invalid token", "10,20"));

        ApplicationComposition composition = new ApplicationComposition();
        KickArraysApplication app = composition.buildApplication();
        app.run(tempInputFile);

        IntegerSequenceRepository repository = IntegerSequenceRepository.getInstance();
        Warehouse warehouse = Warehouse.getInstance();
        List<IntegerSequence> all = repository.getAll();

        assertEquals(2, all.size());

        IntegerSequence first = repository.findById(1L).orElseThrow();
        IntegerSequence third = repository.findById(3L).orElseThrow();

        assertEquals("line-1", first.getName());
        assertEquals("line-3", third.getName());
        assertArrayEquals(new int[] {1, 2, 3}, first.getElements());
        assertArrayEquals(new int[] {10, 20}, third.getElements());

        SequenceStatistics firstStats = warehouse.get(1L).orElseThrow();
        SequenceStatistics thirdStats = warehouse.get(3L).orElseThrow();

        assertEquals(1, firstStats.getMin());
        assertEquals(3, firstStats.getMax());
        assertEquals(6L, firstStats.getSum());
        assertEquals(2.0, firstStats.getAverage());
        assertEquals(3, firstStats.getCount());

        assertEquals(10, thirdStats.getMin());
        assertEquals(20, thirdStats.getMax());
        assertEquals(30L, thirdStats.getSum());
        assertEquals(15.0, thirdStats.getAverage());
        assertEquals(2, thirdStats.getCount());

        assertTrue(warehouse.get(2L).isEmpty());
    }
}
