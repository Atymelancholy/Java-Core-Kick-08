package com.atymelancholy.parser;

public final class RegexConstants {
    public static final String PARAGRAPH_DELIMITER = "\\R{2,}";
    public static final String SENTENCE_REGEX = "[^.!?]+[.!?]|[^.!?]+$";
    public static final String LEXEME_DELIMITER = "\\s+";
    public static final String WORD_REGEX = "[A-Za-z]+(?:[-'][A-Za-z]+)*";
    public static final String SYMBOL_REGEX = ".";

    private RegexConstants() {
    }
}
