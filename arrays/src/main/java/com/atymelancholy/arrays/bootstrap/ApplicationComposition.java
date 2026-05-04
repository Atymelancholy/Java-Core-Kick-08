package com.atymelancholy.arrays.bootstrap;

import com.atymelancholy.arrays.factory.DefaultIntegerSequenceFactory;
import com.atymelancholy.arrays.factory.IntegerSequenceFactory;
import com.atymelancholy.arrays.io.DefaultSequenceDefinitionFileLoader;
import com.atymelancholy.arrays.io.SequenceDefinitionFileLoader;
import com.atymelancholy.arrays.parsing.DefaultIntegerSequenceLineParser;
import com.atymelancholy.arrays.parsing.IntegerSequenceLineParser;
import com.atymelancholy.arrays.repository.IntegerSequenceRepository;
import com.atymelancholy.arrays.service.ArraySortingService;
import com.atymelancholy.arrays.service.AverageService;
import com.atymelancholy.arrays.service.MinimumMaximumService;
import com.atymelancholy.arrays.service.SummationService;
import com.atymelancholy.arrays.service.impl.DefaultArraySortingService;
import com.atymelancholy.arrays.service.impl.DefaultAverageService;
import com.atymelancholy.arrays.service.impl.DefaultMinimumMaximumService;
import com.atymelancholy.arrays.service.impl.DefaultSummationService;
import com.atymelancholy.arrays.validation.ArrayDefinitionValidator;
import com.atymelancholy.arrays.validation.DefaultArrayDefinitionValidator;
import com.atymelancholy.arrays.warehouse.SequenceStatisticsCalculator;
import com.atymelancholy.arrays.warehouse.Warehouse;
import com.atymelancholy.arrays.warehouse.WarehouseRepositoryObserver;

public final class ApplicationComposition {

    public KickArraysApplication buildApplication() {
        SequenceDefinitionFileLoader fileLoader = new DefaultSequenceDefinitionFileLoader();
        ArrayDefinitionValidator validator = new DefaultArrayDefinitionValidator();
        IntegerSequenceLineParser parser = new DefaultIntegerSequenceLineParser(validator);
        IntegerSequenceFactory sequenceFactory = new DefaultIntegerSequenceFactory();
        MinimumMaximumService minimumMaximumService = new DefaultMinimumMaximumService();
        SummationService summationService = new DefaultSummationService();
        AverageService averageService = new DefaultAverageService(summationService);
        ArraySortingService sortingService = new DefaultArraySortingService();
        IntegerSequenceRepository repository = IntegerSequenceRepository.getInstance();
        Warehouse warehouse = Warehouse.getInstance();
        SequenceStatisticsCalculator calculator =
                new SequenceStatisticsCalculator(minimumMaximumService, summationService, averageService);
        repository.addObserver(new WarehouseRepositoryObserver(warehouse, calculator));
        SequenceStatisticsServices statisticsServices =
                new SequenceStatisticsServices(minimumMaximumService, summationService, averageService);
        return new KickArraysApplication(
                fileLoader,
                validator,
                parser,
                sequenceFactory,
                statisticsServices,
                sortingService,
                repository
        );
    }
}
