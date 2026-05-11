package com.atymelancholy.model;

import java.util.Collections;
import java.util.List;

public class LeafTextComponent implements TextComponent {
    private final TextComponentType type;
    private final String value;

    public LeafTextComponent(TextComponentType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Leaf component cannot contain children");
    }
}
