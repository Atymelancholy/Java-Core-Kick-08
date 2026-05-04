package com.atymelancholy.parser;

import java.util.regex.Pattern;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class ParagraphParser extends AbstractTextParser {
    private static final Pattern PARAGRAPH_SPLIT = Pattern.compile(RegexConstants.PARAGRAPH_DELIMITER);

    @Override
    public void parse(String text, TextComponent parent) {
        String[] paragraphs = PARAGRAPH_SPLIT.split(text.strip());
        for (String paragraphText : paragraphs) {
            if (!paragraphText.isBlank()) {
                TextComponent paragraph = new CompositeTextComponent(TextComponentType.PARAGRAPH);
                parent.add(paragraph);
                delegate(paragraphText.strip(), paragraph);
            }
        }
    }
}
