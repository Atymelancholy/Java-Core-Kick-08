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
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.parser.RegexConstants;

public class TaskOperationsService {
    private static final Pattern WORD_PATTERN = Pattern.compile(RegexConstants.WORD_REGEX);
    private final TextStatisticsService statisticsService = new TextStatisticsService();
    private final TextRestorer restorer = new TextRestorer();

    public SentenceOverlapResult findMaxSentenceOverlapByWords(TextComponent text) {
        List<String> sentences = statisticsService.extractSentences(text);
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
        return new SentenceOverlapResult(maxCount, words);
    }

    public List<String> sortSentencesByLetterCount(TextComponent text, char targetLetter) {
        List<String> sentences = statisticsService.extractSentences(text);
        char normalized = Character.toLowerCase(targetLetter);
        return sentences.stream()
            .sorted(Comparator.comparingInt(sentence -> countLetter(sentence, normalized)))
            .toList();
    }

    public String swapFirstAndLastLexemeInEachSentence(TextComponent text) {
        List<String> updatedParagraphs = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            List<String> updatedSentences = new ArrayList<>();
            for (TextComponent sentence : paragraph.getChildren()) {
                List<String> lexemes = sentence.getChildren().stream().map(restorer::restore).toList();
                updatedSentences.add(swapLexemes(lexemes));
            }
            updatedParagraphs.add(String.join(" ", updatedSentences));
        }
        return String.join(System.lineSeparator(), updatedParagraphs);
    }

    private String swapLexemes(List<String> lexemes) {
        if (lexemes.size() < 2) {
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
