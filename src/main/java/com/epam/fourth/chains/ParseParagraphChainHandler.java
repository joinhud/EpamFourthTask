package com.epam.fourth.chains;

import com.epam.fourth.composite.CharacterLeaf;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.ChainHandlerException;
import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseParagraphChainHandler extends AbstractChainHandler {
    private static final String PARAGRAPH_REGEX = "\\p{Blank}+.+\r\n";
    private static final Logger LOG = LogManager.getLogger();

    private ArrayDeque<String> paragraphs;

    public ParseParagraphChainHandler() {
        paragraphs = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) throws ChainHandlerException {
        if(composite == null) {
            throw new ChainHandlerException("TextComposite object is null.");
        }
        if (textForParsing == null) {
            throw new ChainHandlerException("String object is null.");
        }

        Pattern pattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher matcher = pattern.matcher(textForParsing);

        while (matcher.find()) {
            String parsed = matcher.group();
            composite.add(new TextComposite(TextType.PARAGRAPH));
            composite.add(new CharacterLeaf(TextType.CHARACTER, parsed.charAt(parsed.length() - 1)));
            paragraphs.add(parsed);
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        try {
            handleRequest(composite, textForParsing);
            composite.getComponents()
                    .stream()
                    .filter(component -> TextType.PARAGRAPH.equals(component.getType()))
                    .forEach(component -> successor.chain((TextComposite) component, paragraphs.poll()));
        } catch (ChainHandlerException e) {
            LOG.error(e);
        }
    }
}
