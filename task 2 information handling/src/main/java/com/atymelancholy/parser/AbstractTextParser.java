package com.atymelancholy.parser;

import com.atymelancholy.model.TextComponent;

public abstract class AbstractTextParser implements TextParser {
    private TextParser nextParser;

    @Override
    public void setNext(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    protected void delegate(String text, TextComponent parent) {
        if (nextParser != null) {
            nextParser.parse(text, parent);
        }
    }
}
