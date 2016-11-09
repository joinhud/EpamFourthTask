package com.epam.fourth.composite;

import com.epam.fourth.type.TextType;

public class CharacterLeaf implements TextComponent {
    private TextType type; //лишнее
    private Character value;

    public CharacterLeaf(TextType type, char value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public TextType getType() {
        return type;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
