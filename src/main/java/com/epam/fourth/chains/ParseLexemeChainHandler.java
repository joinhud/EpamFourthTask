package com.epam.fourth.chains;

import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.ChainHandlerException;
import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseLexemeChainHandler extends AbstractChainHandler {
    private static final String LEXEME_REGEX = "\\p{Space}\\p{Graph}+\\p{P}?";
    private static final Logger LOG = LogManager.getLogger();

    private ArrayDeque<String> lexemes;

    public ParseLexemeChainHandler() {
        lexemes = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) throws ChainHandlerException {
        if(composite == null) {
            throw new ChainHandlerException("TextComposite object is null.");
        }

        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(textForParsing);

        while (matcher.find()) {
            composite.add(new TextComposite(TextType.LEXEME));
            lexemes.add(matcher.group());
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        try {
            handleRequest(composite, textForParsing);
            composite.getComponents()
                    .stream()
                    .filter(component -> component.getType().equals(TextType.LEXEME))
                    .forEach(component -> successor.chain((TextComposite) component, lexemes.poll()));
        } catch (ChainHandlerException e) {
            LOG.error(e);
        }
    }
}
