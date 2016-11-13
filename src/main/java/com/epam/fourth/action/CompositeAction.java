package com.epam.fourth.action;

import com.epam.fourth.composite.TextComponent;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeActionException;
import com.epam.fourth.type.TextType;

import java.util.ArrayList;

public class CompositeAction {
    private ArrayList<TextComponent> components;

    public boolean checkTextComponent(TextComponent component) {
        return component != null;
    }

    public ArrayList<TextComponent> getComponentsByType(TextComposite text, TextType type) throws CompositeActionException {
        if (!checkTextComponent(text)) {
            throw new CompositeActionException("TextComposite object is null!");
        }

        components = new ArrayList<>();
        findComponentsByType(text, type);

        return components;
    }

    public int calculateLexemesCount(TextComponent component) throws CompositeActionException {
        if (!checkTextComponent(component)) {
            throw new CompositeActionException("TextComponent object is null!");
        }

        int count = 0;

        for (TextComponent lexeme : ((TextComposite) component).getComponents()) {
            if (lexeme.getType().equals(TextType.LEXEME)) {
                count++;
            }
        }

        return count;
    }

    private void findComponentsByType(TextComposite composite, TextType type) throws CompositeActionException {
        if (!checkTextComponent(composite)) {
            throw new CompositeActionException("TextComposite object is null!");
        }

        for (TextComponent component : composite.getComponents()) {
            if(component.getType().equals(type)) {
                components.add(component);
            }
            if(component instanceof TextComposite) {
                findComponentsByType((TextComposite) component, type);
            }
        }
    }
}
