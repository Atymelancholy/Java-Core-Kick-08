package com.atymelancholy.parser;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atymelancholy.model.CompositeTextComponent;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class ParagraphParser extends AbstractTextParser {
    private static final Logger logger = LoggerFactory.getLogger(ParagraphParser.class);
    private static final Pattern PARAGRAPH_SPLIT = Pattern.compile(RegexConstants.PARAGRAPH_DELIMITER);

    @Override
    public void parse(String text, TextComponent parent) {
        logger.debug("Parsing paragraphs from text segment (length: {})", text.length());

        String[] paragraphs = PARAGRAPH_SPLIT.split(text.strip());
        logger.debug("Found {} paragraph(s)", paragraphs.length);

        for (String paragraphText : paragraphs) {
            if (!paragraphText.isBlank()) {
                TextComponent paragraph = new CompositeTextComponent(TextComponentType.PARAGRAPH);
                parent.add(paragraph);
                logger.trace("Created paragraph component, delegating to next parser");
                delegate(paragraphText.strip(), paragraph);
            }
        }
    }
}