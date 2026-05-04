package com.atymelancholy;

import com.atymelancholy.model.TextComponent;
import com.atymelancholy.service.TaskOperationsService;
import com.atymelancholy.service.TextFileService;
import com.atymelancholy.service.TextParserService;
import com.atymelancholy.service.TextRestorer;
import com.atymelancholy.service.TextStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String DEFAULT_INPUT_FILE = "input/sample-text.txt";

    public static void main(String[] args) {
        TextFileService textFileService = new TextFileService();
        TextParserService parserService = new TextParserService();
        TextRestorer restorer = new TextRestorer();
        TextStatisticsService statisticsService = new TextStatisticsService();
        TaskOperationsService operationsService = new TaskOperationsService();

        String inputFilePath = resolveInputFilePath(args);
        LOGGER.info("Input file: {}", inputFilePath);

        String rawText = textFileService.readText(inputFilePath);
        TextComponent parsedText = parserService.parse(rawText);

        LOGGER.info("Restored text:\n{}", restorer.restore(parsedText));
        LOGGER.info("Letter count: {}", statisticsService.countLetters(parsedText));
        LOGGER.info("Symbol count: {}", statisticsService.countSymbols(parsedText));

        TaskOperationsService.SentenceOverlapResult overlapResult =
            operationsService.findMaxSentenceOverlapByWords(parsedText);
        LOGGER.info("Max sentence overlap count: {}, words: {}", overlapResult.sentenceCount(), overlapResult.words());

        LOGGER.info("Sentences sorted by letter 'a': {}", operationsService.sortSentencesByLetterCount(parsedText, 'a'));
        LOGGER.info("Text after swapping first/last lexeme:\n{}",
            operationsService.swapFirstAndLastLexemeInEachSentence(parsedText));
    }

    private static String resolveInputFilePath(String[] args) {
        if (args.length == 0 || args[0].isBlank()) {
            LOGGER.warn("No input file argument provided. Falling back to default file: {}", DEFAULT_INPUT_FILE);
            return DEFAULT_INPUT_FILE;
        }
        return args[0];
    }
}