package com.atymelancholy.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class TextStatisticsService {
    private static final Logger logger = LoggerFactory.getLogger(TextStatisticsService.class);
    private final TextRestorer restorer = new TextRestorer();

    public int countLetters(TextComponent text) {
        logger.info("Counting letters in text");
        String restored = restorer.restore(text);
        int count = 0;
        for (char c : restored.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        logger.info("Total letters count: {} (out of {} total characters)", count, restored.length());
        return count;
    }

    public int countSymbols(TextComponent text) {
        logger.info("Counting all symbols in text");
        String restored = restorer.restore(text);
        logger.info("Total symbols count: {}", restored.length());
        return restored.length();
    }

    public List<String> extractSentences(TextComponent text) {
        logger.debug("Extracting all sentences from text structure");
        List<String> sentences = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                sentences.add(restorer.restore(sentence));
            }
        }
        logger.debug("Extracted {} sentences", sentences.size());
        return sentences;
    }

    public List<TextComponent> extractSentenceComponents(TextComponent text) {
        logger.debug("Extracting sentence components from text structure");
        List<TextComponent> sentenceComponents = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                if (sentence.getType() == TextComponentType.SENTENCE) {
                    sentenceComponents.add(sentence);
                }
            }
        }
        logger.debug("Extracted {} sentence components", sentenceComponents.size());
        return sentenceComponents;
    }
}