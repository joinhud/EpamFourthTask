package com.epam.fourth.chains;

import com.epam.fourth.composite.CharacterLeaf;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.ChainHandlerException;
import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseCharacterChainHandler extends AbstractChainHandler {
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) throws ChainHandlerException {
        if (composite == null) {
            throw new ChainHandlerException("TextComposite object is null.");
        }
        if (textForParsing == null) {
            throw new ChainHandlerException("String object is null.");
        }

        for (char val : textForParsing.toCharArray()) {
            composite.add(new CharacterLeaf(TextType.CHARACTER, val));
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        try {
            handleRequest(composite, textForParsing);
        } catch (ChainHandlerException e) {
            LOG.error(e);
        }
    }
}
