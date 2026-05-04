package com.atymelancholy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompositeTextComponent implements TextComponent {
    private final TextComponentType type;
    private final List<TextComponent> children = new ArrayList<>();

    public CompositeTextComponent(TextComponentType type) {
        this.type = type;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }
}
