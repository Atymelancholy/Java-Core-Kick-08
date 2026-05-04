package com.atymelancholy.service;

import java.util.List;
import java.util.stream.Collectors;
import com.atymelancholy.model.TextComponent;
import com.atymelancholy.model.TextComponentType;

public class TextRestorer {
    public String restore(TextComponent component) {
        if (component.getType() == TextComponentType.WORD || component.getType() == TextComponentType.SYMBOL) {
            return component.getValue();
        }
        List<TextComponent> children = component.getChildren();
        if (children.isEmpty()) {
            return component.getValue();
        }

        return switch (component.getType()) {
            case TEXT -> joinChildren(children, System.lineSeparator());
            case PARAGRAPH -> joinChildren(children, " ");
            case SENTENCE -> joinChildren(children, " ");
            case LEXEME -> joinChildren(children, "");
            default -> joinChildren(children, "");
        };
    }

    private String joinChildren(List<TextComponent> children, String delimiter) {
        return children.stream().map(this::restore).collect(Collectors.joining(delimiter)).strip();
    }
}
