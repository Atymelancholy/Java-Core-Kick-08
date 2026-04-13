package com.javakick.arrays.bootstrap;

import com.javakick.arrays.entity.IntegerSequence;
import com.javakick.arrays.exception.InvalidSequenceDefinitionException;
import com.javakick.arrays.factory.IntegerSequenceFactory;
import com.javakick.arrays.io.SequenceDefinitionFileLoader;
import com.javakick.arrays.parsing.IntegerSequenceLineParser;
import com.javakick.arrays.service.AverageService;
import com.javakick.arrays.service.MinimumMaximumService;
import com.javakick.arrays.service.SummationService;
import com.javakick.arrays.validation.ArrayDefinitionValidator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class KickArraysApplication {

    private final Logger logger = LogManager.getLogger(KickArraysApplication.class);

    private final SequenceDefinitionFileLoader fileLoader;
    private final ArrayDefinitionValidator validator;
    private final IntegerSequenceLineParser parser;
    private final IntegerSequenceFactory sequenceFactory;
    private final MinimumMaximumService minimumMaximumService;
    private final SummationService summationService;
    private final AverageService averageService;

    public KickArraysApplication(
            SequenceDefinitionFileLoader fileLoader,
            ArrayDefinitionValidator validator,
            IntegerSequenceLineParser parser,
            IntegerSequenceFactory sequenceFactory,
            MinimumMaximumService minimumMaximumService,
            SummationService summationService,
            AverageService averageService) {
        this.fileLoader = fileLoader;
        this.validator = validator;
        this.parser = parser;
        this.sequenceFactory = sequenceFactory;
        this.minimumMaximumService = minimumMaximumService;
        this.summationService = summationService;
        this.averageService = averageService;
    }

    public void run(Path definitionFile) {
        try {
            List<String> lines = fileLoader.loadLines(definitionFile);
            processLines(lines);
        } catch (IOException ex) {
            logger.error("Could not load definition file: {}", definitionFile, ex);
        }
    }

    private void processLines(List<String> lines) {
        int lineIndex = 0;
        for (String rawLine : lines) {
            lineIndex++;
            if (validator.isValid(rawLine)) {
                handleValidLine(lineIndex, rawLine);
            } else {
                logger.warn("Skipping invalid line {}: {}", lineIndex, rawLine.trim());
            }
        }
    }

    private void handleValidLine(int lineIndex, String rawLine) {
        try {
            int[] values = parser.parseToPrimitiveArray(rawLine);
            IntegerSequence sequence = sequenceFactory.create(values);
            logStatistics(lineIndex, sequence);
        } catch (InvalidSequenceDefinitionException ex) {
            logger.error("Unexpected parse failure at line {}", lineIndex, ex);
        }
    }

    private void logStatistics(int lineIndex, IntegerSequence sequence) {
        Optional<Integer> minimum = minimumMaximumService.findMinimum(sequence);
        Optional<Integer> maximum = minimumMaximumService.findMaximum(sequence);
        Optional<Long> total = summationService.sum(sequence);
        Optional<Double> mean = averageService.average(sequence);
        logger.info(
                "Line {} length={} min={} max={} sum={} average={}",
                lineIndex,
                sequence.getLength(),
                minimum.map(Object::toString).orElse("n/a"),
                maximum.map(Object::toString).orElse("n/a"),
                total.map(Object::toString).orElse("n/a"),
                mean.map(Object::toString).orElse("n/a"));
    }
}
