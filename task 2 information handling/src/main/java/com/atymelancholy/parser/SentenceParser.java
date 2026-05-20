package com.atymelancholy.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class SentenceParser extends AbstractTextParser {
    private static final Logger logger = LoggerFactory.getLogger(SentenceParser.class);
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(RegexConstants.SENTENCE_REGEX);

    @Override
    public void parse(String text, TextComponent parent) {
        logger.debug("Parsing sentences from text: {}",
                text.length() > 50 ? text.substring(0, 50) + "..." : text);

        Matcher matcher = SENTENCE_PATTERN.matcher(text);
        int sentenceCount = 0;

        while (matcher.find()) {
            String sentenceText = matcher.group().strip();
            if (!sentenceText.isEmpty()) {
                sentenceCount++;
                TextComponent sentence = new CompositeTextComponent(TextComponentType.SENTENCE);
                parent.add(sentence);
                logger.trace("Found sentence #{}: {}", sentenceCount,
                        sentenceText.length() > 30 ? sentenceText.substring(0, 30) + "..." : sentenceText);
                delegate(sentenceText, sentence);
            }
        }

        logger.debug("Total sentences parsed: {}", sentenceCount);
    }
}