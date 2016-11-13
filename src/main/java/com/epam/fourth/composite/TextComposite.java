package com.epam.fourth.composite;

import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class TextComposite implements TextComponent {
    private TextType type;
    private ArrayList<TextComponent> components;
    private static final Logger LOG = LogManager.getLogger();

    public TextComposite(TextType type) {
        this.type = type;
        components = new ArrayList<>();
    }

    public ArrayList<TextComponent> getComponents() {
        return components;
    }

    public void add(TextComponent component) {
        if(!components.add(component)) {
            LOG.info("Can't add TextComponent into TextComposit.");
        }
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
