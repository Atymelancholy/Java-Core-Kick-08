package com.atymelancholy.parser;

public final class ParserChainFactory {
    private ParserChainFactory() {
    }

    public static TextParser createTextParserChain() {
        TextParser paragraphParser = new ParagraphParser();
        TextParser sentenceParser = new SentenceParser();
        TextParser lexemeParser = new LexemeParser();
        TextParser wordSymbolParser = new WordSymbolParser();

        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordSymbolParser);
        return paragraphParser;
    }
}
