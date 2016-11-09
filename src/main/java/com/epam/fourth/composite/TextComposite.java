package com.epam.fourth.composite;

import com.epam.fourth.type.TextType;

import java.util.ArrayList;

public class TextComposite implements TextComponent {
    private TextType type;
    private ArrayList<TextComponent> components;

    public TextComposite(TextType type) {
        this.type = type;
        components = new ArrayList<>();
    }

    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    public void add(TextComponent component) {
        components.add(component);
    }

    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public TextType getType() {
        return type;
    }

    @Override
    public String toString() {
        String returnValue = "";

        for(TextComponent component : components) {
            returnValue += component.toString();
        }

        return returnValue;
    }
}
