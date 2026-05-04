package com.atymelancholy.service;

import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;
import com.atymelancholy.parser.ParserChainFactory;
import com.atymelancholy.parser.TextParser;

public class TextParserService {
    public TextComponent parse(String rawText) {
        TextComponent text = new CompositeTextComponent(TextComponentType.TEXT);
        TextParser parserChain = ParserChainFactory.createTextParserChain();
        parserChain.parse(rawText, text);
        return text;
    }
}
