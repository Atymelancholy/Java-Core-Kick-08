package com.atymelancholy.arrays.bootstrap;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.exception.InvalidSequenceDefinitionException;
import com.atymelancholy.arrays.factory.IntegerSequenceFactory;
import com.atymelancholy.arrays.io.SequenceDefinitionFileLoader;
import com.atymelancholy.arrays.parsing.IntegerSequenceLineParser;
import com.atymelancholy.arrays.repository.IntegerSequenceRepository;
import com.atymelancholy.arrays.service.ArraySortingService;
import com.atymelancholy.arrays.validation.ArrayDefinitionValidator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
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
    private final SequenceStatisticsServices statisticsServices;
    private final ArraySortingService sortingService;
    private final IntegerSequenceRepository repository;

    public KickArraysApplication(
            SequenceDefinitionFileLoader fileLoader,
            ArrayDefinitionValidator validator,
            IntegerSequenceLineParser parser,
            IntegerSequenceFactory sequenceFactory,
            SequenceStatisticsServices statisticsServices,
            ArraySortingService sortingService,
            IntegerSequenceRepository repository) {
        this.fileLoader = fileLoader;
        this.validator = validator;
        this.parser = parser;
        this.sequenceFactory = sequenceFactory;
        this.statisticsServices = statisticsServices;
        this.sortingService = sortingService;
        this.repository = repository;
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
                if (logger.isWarnEnabled()) {
                    logger.warn("Skipping invalid line {}: {}", lineIndex, rawLine.strip());
                }
            }
        }
    }

    private void handleValidLine(int lineIndex, String rawLine) {
        try {
            int[] values = parser.parseToPrimitiveArray(rawLine);
            IntegerSequence sequence = sequenceFactory.create(lineIndex, "line-" + lineIndex, values);
            repository.add(sequence);
            logStatistics(lineIndex, sequence);
        } catch (InvalidSequenceDefinitionException ex) {
            logger.error("Unexpected parse failure at line {}", lineIndex, ex);
        }
    }

    private void logStatistics(int lineIndex, IntegerSequence sequence) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        Optional<Integer> minimum = statisticsServices.minimumMaximumService().findMinimum(sequence);
        Optional<Integer> maximum = statisticsServices.minimumMaximumService().findMaximum(sequence);
        Optional<Long> total = statisticsServices.summationService().sum(sequence);
        Optional<Double> mean = statisticsServices.averageService().average(sequence);
        int[] bubbleSorted = sortingService.sortByBubbleSort(sequence);
        int[] insertionSorted = sortingService.sortByInsertionSort(sequence);
        logger.info(
                "Line {} length={} min={} max={} sum={} average={} bubbleSorted={} insertionSorted={}",
                lineIndex,
                sequence.getLength(),
                minimum.map(Object::toString).orElse("n/a"),
                maximum.map(Object::toString).orElse("n/a"),
                total.map(Object::toString).orElse("n/a"),
                mean.map(Object::toString).orElse("n/a"),
                Arrays.toString(bubbleSorted),
                Arrays.toString(insertionSorted));
    }
}
