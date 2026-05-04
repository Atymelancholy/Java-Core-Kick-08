package com.atymelancholy.parser;

import java.util.regex.Pattern;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class LexemeParser extends AbstractTextParser {
    private static final Pattern LEXEME_SPLIT = Pattern.compile(RegexConstants.LEXEME_DELIMITER);

    @Override
    public void parse(String text, TextComponent parent) {
        String[] lexemes = LEXEME_SPLIT.split(text.strip());
        for (String lexemeText : lexemes) {
            if (!lexemeText.isBlank()) {
                TextComponent lexeme = new CompositeTextComponent(TextComponentType.LEXEME);
                parent.add(lexeme);
                delegate(lexemeText, lexeme);
            }
        }
    }
}
