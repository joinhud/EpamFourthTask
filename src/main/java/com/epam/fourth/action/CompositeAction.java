package com.epam.fourth.action;

import com.epam.fourth.composite.TextComponent;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeException;
import com.epam.fourth.type.TextType;

import java.util.ArrayList;

public class CompositeAction {
    private ArrayList<TextComponent> components;

    public ArrayList<TextComponent> getComponentsByType(TextComposite text, TextType type) throws CompositeException {
        if (!checkTextComponent(text)) {
            throw new CompositeException("TextComposite object is null.");
        }
        if (!checkTextType(type)) {
            throw new CompositeException("TextType object is null.");
        }

        components = new ArrayList<>();
        findComponentsByType(text, type);

        return components;
    }

    public int calculateLexemesCount(TextComponent component) throws CompositeException {
        if (!checkTextComponent(component)) {
            throw new CompositeException("TextComponent object is null!");
        }

        int count = 0;

        for (TextComponent lexeme : ((TextComposite) component).getComponents()) {
            if (TextType.LEXEME.equals(lexeme.getType())) {
                count++;
            }
        }

        return count;
    }

    private void findComponentsByType(TextComposite composite, TextType type) {
        for (TextComponent component : composite.getComponents()) {
            if(component.getType().equals(type)) {
                components.add(component);
            }
            if(component instanceof TextComposite) {
                findComponentsByType((TextComposite) component, type);
            }
        }
    }

    private boolean checkTextComponent(TextComponent component) {
        return component != null;
    }

    private boolean checkTextType(TextType type) {
        return type != null;
    }
}
