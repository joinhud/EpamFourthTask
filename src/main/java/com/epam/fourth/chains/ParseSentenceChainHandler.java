package com.epam.fourth.chains;

import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.ChainHandlerException;
import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseSentenceChainHandler extends AbstractChainHandler {
    private static final String SENTENCE_REGEX = "\\p{Space}\\p{Upper}([^.!?]+)[.!?]+";
    private static final Logger LOG = LogManager.getLogger();

    private ArrayDeque<String> sentences;

    public ParseSentenceChainHandler() {
        sentences = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) throws ChainHandlerException {
        if(composite == null) {
            throw new ChainHandlerException("TextComposite object is null.");
        }
        if (textForParsing == null) {
            throw new ChainHandlerException("String object is null.");
        }

        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(textForParsing);

        while (matcher.find()) {
            composite.add(new TextComposite(TextType.SENTENCE));
            sentences.add(matcher.group());
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        try {
            handleRequest(composite, textForParsing);
            composite.getComponents()
                    .stream()
                    .filter(component -> TextType.SENTENCE.equals(component.getType()))
                    .forEach(component -> successor.chain((TextComposite) component, sentences.poll()));
        } catch (ChainHandlerException e) {
            LOG.error(e);
        }
    }
}
