package com.atymelancholy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import com.atymelancholy.model.TextComponent;
import org.junit.jupiter.api.Test;

class TextProcessingTest {
    private static final String SAMPLE_TEXT =
        "Alpha beta gamma. Beta alpha delta! Gamma beta alpha.";
    private static final String SPACED_TEXT = "Alpha   beta\tgamma.";
    private static final String SHORT_TEXT = "Abc!";
    private static final String SWAP_TEXT = "One two three. Four five six.";

    private final TextParserService parserService = new TextParserService();
    private final TextRestorer restorer = new TextRestorer();
    private final TextStatisticsService statisticsService = new TextStatisticsService();
    private final TaskOperationsService operationsService = new TaskOperationsService();

    @Test
    void shouldParseAndRestoreTextWithNormalizedSpaces() {
        String input = SPACED_TEXT;
        TextComponent text = parserService.parse(input);
        assertEquals("Alpha beta gamma.", restorer.restore(text));
    }

    @Test
    void shouldCountLettersAndSymbols() {
        String input = SHORT_TEXT;
        TextComponent text = parserService.parse(input);
        assertEquals(3, statisticsService.countLetters(text));
        assertEquals(4, statisticsService.countSymbols(text));
    }

    @Test
    void shouldFindMaxSentenceWordOverlap() {
        TextComponent text = parserService.parse(SAMPLE_TEXT);
        TaskOperationsService.SentenceOverlapResult result = operationsService.findMaxSentenceOverlapByWords(text);
        assertEquals(3, result.sentenceCount());
        assertTrue(result.words().contains("alpha"));
        assertTrue(result.words().contains("beta"));
    }

    @Test
    void shouldSortSentencesByLetterCount() {
        TextComponent text = parserService.parse(SAMPLE_TEXT);
        List<String> sorted = operationsService.sortSentencesByLetterCount(text, 'd');
        assertEquals("Alpha beta gamma.", sorted.getFirst());
        assertEquals("Gamma beta alpha.", sorted.get(1));
        assertEquals("Beta alpha delta!", sorted.getLast());
    }

    @Test
    void shouldSwapFirstAndLastLexemeInEachSentence() {
        TextComponent text = parserService.parse(SWAP_TEXT);
        String updated = operationsService.swapFirstAndLastLexemeInEachSentence(text);
        assertEquals("three. two One six. five Four", updated);
    }
}
