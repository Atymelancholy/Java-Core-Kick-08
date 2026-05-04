package com.atymelancholy.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class SentenceParser extends AbstractTextParser {
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(RegexConstants.SENTENCE_REGEX);

    @Override
    public void parse(String text, TextComponent parent) {
        Matcher matcher = SENTENCE_PATTERN.matcher(text);
        while (matcher.find()) {
            String sentenceText = matcher.group().strip();
            TextComponent sentence = new CompositeTextComponent(TextComponentType.SENTENCE);
            parent.add(sentence);
            delegate(sentenceText, sentence);
        }
    }
}
