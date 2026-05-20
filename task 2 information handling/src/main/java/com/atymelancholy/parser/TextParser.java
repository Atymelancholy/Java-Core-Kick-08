package com.atymelancholy.parser;

import com.atymelancholy.model.TextComponent;

public interface TextParser {
    void setNext(TextParser nextParser);

    void parse(String text, TextComponent parent);
}
