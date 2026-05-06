package com.atymelancholy.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.LeafTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class WordSymbolParser extends AbstractTextParser {
    private static final Logger logger = LoggerFactory.getLogger(WordSymbolParser.class);
    private static final Pattern WORD_PATTERN = Pattern.compile(RegexConstants.WORD_REGEX);
    private static final Pattern SYMBOL_PATTERN = Pattern.compile(RegexConstants.SYMBOL_REGEX);

    @Override
    public void parse(String text, TextComponent parent) {
        logger.trace("Parsing word/symbol from: {}", text);

        Matcher wordMatcher = WORD_PATTERN.matcher(text);
        if (wordMatcher.matches()) {
            logger.trace("Component identified as WORD: {}", text);
            parent.add(new LeafTextComponent(TextComponentType.WORD, text));
            return;
        }

        Matcher symbolMatcher = SYMBOL_PATTERN.matcher(text);
        int symbolCount = 0;

        while (symbolMatcher.find()) {
            String symbol = symbolMatcher.group();
            TextComponentType type = WORD_PATTERN.matcher(symbol).matches()
                    ? TextComponentType.WORD
                    : TextComponentType.SYMBOL;
            logger.trace("Component identified as {}: {}", type, symbol);
            parent.add(new LeafTextComponent(type, symbol));
            symbolCount++;
        }

        logger.trace("Processed {} symbol(s)", symbolCount);
    }
}