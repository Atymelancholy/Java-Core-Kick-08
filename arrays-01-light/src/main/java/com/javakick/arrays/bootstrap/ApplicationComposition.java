package com.javakick.arrays.bootstrap;

import com.javakick.arrays.factory.DefaultIntegerSequenceFactory;
import com.javakick.arrays.factory.IntegerSequenceFactory;
import com.javakick.arrays.io.DefaultSequenceDefinitionFileLoader;
import com.javakick.arrays.io.SequenceDefinitionFileLoader;
import com.javakick.arrays.parsing.DefaultIntegerSequenceLineParser;
import com.javakick.arrays.parsing.IntegerSequenceLineParser;
import com.javakick.arrays.service.AverageService;
import com.javakick.arrays.service.MinimumMaximumService;
import com.javakick.arrays.service.SummationService;
import com.javakick.arrays.service.impl.DefaultAverageService;
import com.javakick.arrays.service.impl.DefaultMinimumMaximumService;
import com.javakick.arrays.service.impl.DefaultSummationService;
import com.javakick.arrays.validation.ArrayDefinitionValidator;
import com.javakick.arrays.validation.DefaultArrayDefinitionValidator;

public final class ApplicationComposition {

    public KickArraysApplication buildApplication() {
        SequenceDefinitionFileLoader fileLoader = new DefaultSequenceDefinitionFileLoader();
        ArrayDefinitionValidator validator = new DefaultArrayDefinitionValidator();
        IntegerSequenceLineParser parser = new DefaultIntegerSequenceLineParser(validator);
        IntegerSequenceFactory sequenceFactory = new DefaultIntegerSequenceFactory();
        MinimumMaximumService minimumMaximumService = new DefaultMinimumMaximumService();
        SummationService summationService = new DefaultSummationService();
        AverageService averageService = new DefaultAverageService(summationService);
        return new KickArraysApplication(
                fileLoader,
                validator,
                parser,
                sequenceFactory,
                minimumMaximumService,
                summationService,
                averageService
        );
    }
}
