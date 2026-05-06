package com.atymelancholy.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ParserChainFactory {
    private static final Logger logger = LoggerFactory.getLogger(ParserChainFactory.class);

    private ParserChainFactory() {
    }

    public static TextParser createTextParserChain() {
        logger.info("Creating parser chain: ParagraphParser → SentenceParser → LexemeParser → WordSymbolParser");

        TextParser paragraphParser = new ParagraphParser();
        TextParser sentenceParser = new SentenceParser();
        TextParser lexemeParser = new LexemeParser();
        TextParser wordSymbolParser = new WordSymbolParser();

        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordSymbolParser);

        logger.debug("Parser chain created successfully");
        return paragraphParser;
    }
}