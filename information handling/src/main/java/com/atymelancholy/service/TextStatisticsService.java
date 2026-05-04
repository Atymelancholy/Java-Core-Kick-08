package com.atymelancholy.service;

import java.util.ArrayList;
import java.util.List;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class TextStatisticsService {
    public int countLetters(TextComponent text) {
        String restored = new TextRestorer().restore(text);
        int count = 0;
        for (char c : restored.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        return count;
    }

    public int countSymbols(TextComponent text) {
        String restored = new TextRestorer().restore(text);
        return restored.length();
    }

    public List<String> extractSentences(TextComponent text) {
        List<String> sentences = new ArrayList<>();
        TextRestorer restorer = new TextRestorer();
        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                sentences.add(restorer.restore(sentence));
            }
        }
        return sentences;
    }

    public List<TextComponent> extractSentenceComponents(TextComponent text) {
        List<TextComponent> sentenceComponents = new ArrayList<>();
        for (TextComponent paragraph : text.getChildren()) {
            for (TextComponent sentence : paragraph.getChildren()) {
                if (sentence.getType() == TextComponentType.SENTENCE) {
                    sentenceComponents.add(sentence);
                }
            }
        }
        return sentenceComponents;
    }
}
