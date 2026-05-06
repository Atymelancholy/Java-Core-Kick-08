package com.atymelancholy.parser;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class LexemeParser extends AbstractTextParser {
    private static final Logger logger = LoggerFactory.getLogger(LexemeParser.class);
    private static final Pattern LEXEME_SPLIT = Pattern.compile(RegexConstants.LEXEME_DELIMITER);

    @Override
    public void parse(String text, TextComponent parent) {
        logger.debug("Parsing lexemes from text: {}",
                text.length() > 30 ? text.substring(0, 30) + "..." : text);

        String[] lexemes = LEXEME_SPLIT.split(text.strip());
        logger.trace("Found {} lexeme(s)", lexemes.length);

        for (String lexemeText : lexemes) {
            if (!lexemeText.isBlank()) {
                TextComponent lexeme = new CompositeTextComponent(TextComponentType.LEXEME);
                parent.add(lexeme);
                delegate(lexemeText, lexeme);
            }
        }
    }
}