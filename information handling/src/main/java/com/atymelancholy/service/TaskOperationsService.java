package com.atymelancholy.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.parser.RegexConstants;

public class TaskOperationsService {
    private static final Logger logger = LoggerFactory.getLogger(TaskOperationsService.class);
    private static final Pattern WORD_PATTERN = Pattern.compile(RegexConstants.WORD_REGEX);
    private final TextStatisticsService statisticsService = new TextStatisticsService();
    private final TextRestorer restorer = new TextRestorer();

    public SentenceOverlapResult findMaxSentenceOverlapByWords(TextComponent text) {
        logger.info("Finding maximum sentence overlap by words");
        long startTime = System.currentTimeMillis();

        List<String> sentences = statisticsService.extractSentences(text);
        logger.debug("Processing {} sentences", sentences.size());

        Map<String, Integer> sentenceCountByWord = new HashMap<>();
        for (String sentence : sentences) {
            Set<String> uniqueWords = new HashSet<>(extractWords(sentence));
            for (String word : uniqueWords) {
                sentenceCountByWord.put(word, sentenceCountByWord.getOrDefault(word, 0) + 1);
            }
        }

        int maxCount = 0;
        List<String> words = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sentenceCountByWord.entrySet()) {
            int value = entry.getValue();
            if (value > maxCount) {
                maxCount = value;
                words.clear();
                words.add(entry.getKey());
            } else if (value == maxCount) {
                words.add(entry.getKey());
            }
        }

        words.sort(String::compareTo);
        long duration = System.currentTimeMillis() - startTime;

        logger.info("Max overlap found: {} sentence(s) with word(s): {} (took {} ms)",
                maxCount, words, duration);

        return new SentenceOverlapResult(maxCount, words);
    }

    public List<String> sortSentencesByLetterCount(TextComponent text, char targetLetter) {
        logger.info("Sorting sentences by count of letter: '{}'", targetLetter);
        long startTime = System.currentTimeMillis();

        List<String> sentences = statisticsService.extractSentences(text);
        char normalized = Character.toLowerCase(targetLetter);

        List<String> sorted = sentences.stream()
                .sorted(Comparator.comparingInt(sentence -> countLetter(sentence, normalized)))
                .toList();

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Sorted {} sentences by letter '{}' count (took {} ms)",
                sentences.size(), targetLetter, duration);

        if (logger.isDebugEnabled()) {
            logger.debug("First sentence (least occurrences): {}",
                    sorted.isEmpty() ? "none" : sorted.get(0));
            logger.debug("Last sentence (most occurrences): {}",
                    sorted.isEmpty() ? "none" : sorted.get(sorted.size() - 1));
        }

        return sorted;
    }

    public String swapFirstAndLastLexemeInEachSentence(TextComponent text) {
        logger.info("Swapping first and last lexeme in each sentence");
        long startTime = System.currentTimeMillis();

        List<String> updatedParagraphs = new ArrayList<>();
        int totalSentences = 0;
        int swappedCount = 0;

        for (TextComponent paragraph : text.getChildren()) {
            List<String> updatedSentences = new ArrayList<>();
            for (TextComponent sentence : paragraph.getChildren()) {
                totalSentences++;
                List<String> lexemes = sentence.getChildren().stream().map(restorer::restore).toList();
                String swapped = swapLexemes(lexemes);
                updatedSentences.add(swapped);
                if (lexemes.size() >= 2) {
                    swappedCount++;
                    logger.trace("Swapped lexemes in sentence #{}: {} -> {}",
                            totalSentences, lexemes, swapped);
                }
            }
            updatedParagraphs.add(String.join(" ", updatedSentences));
        }

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Swapped lexemes in {} out of {} sentences (took {} ms)",
                swappedCount, totalSentences, duration);

        return String.join(System.lineSeparator(), updatedParagraphs);
    }

    private String swapLexemes(List<String> lexemes) {
        if (lexemes.size() < 2) {
            logger.trace("Sentence has less than 2 lexemes, no swap performed");
            return String.join(" ", lexemes);
        }
        List<String> mutable = new ArrayList<>(lexemes);
        String first = mutable.getFirst();
        String last = mutable.getLast();
        mutable.set(0, last);
        mutable.set(mutable.size() - 1, first);
        return String.join(" ", mutable);
    }

    private int countLetter(String sentence, char target) {
        int count = 0;
        for (char c : sentence.toLowerCase(Locale.ROOT).toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }

    private List<String> extractWords(String sentence) {
        List<String> words = new ArrayList<>();
        Matcher matcher = WORD_PATTERN.matcher(sentence.toLowerCase(Locale.ROOT));
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }

    public record SentenceOverlapResult(int sentenceCount, List<String> words) {
    }
}