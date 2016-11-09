package com.epam.fourth.chains;

import com.epam.fourth.composite.CharacterLeaf;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.type.TextType;

public class ParseCharacterChainHandler extends AbstractChainHandler {

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) {
        for (char val : textForParsing.toCharArray()) {
            composite.add(new CharacterLeaf(TextType.CHARACTER, val));
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        handleRequest(composite, textForParsing);
    }
}
