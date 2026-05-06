package com.atymelancholy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;
import com.atymelancholy.parser.ParserChainFactory;
import com.atymelancholy.parser.TextParser;

public class TextParserService {
    private static final Logger logger = LoggerFactory.getLogger(TextParserService.class);

    public TextComponent parse(String rawText) {
        logger.info("Starting text parsing. Input text length: {} characters", rawText.length());
        long startTime = System.currentTimeMillis();

        TextComponent text = new CompositeTextComponent(TextComponentType.TEXT);
        TextParser parserChain = ParserChainFactory.createTextParserChain();
        parserChain.parse(rawText, text);

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Text parsing completed in {} ms. Text structure: {}",
                duration, text.getClass().getSimpleName());
        logger.debug("Parsed text has {} paragraphs", text.getChildren().size());

        return text;
    }
}