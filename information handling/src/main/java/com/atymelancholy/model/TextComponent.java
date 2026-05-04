package com.atymelancholy.model;

import java.util.List;

public interface TextComponent {
    TextComponentType getType();

    String getValue();

    List<TextComponent> getChildren();

    void add(TextComponent component);
}
