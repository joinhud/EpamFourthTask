package com.epam.fourth.chains;

import com.epam.fourth.composite.CharacterLeaf;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.converter.PolishFormConverter;
import com.epam.fourth.exception.ChainHandlerException;
import com.epam.fourth.interpreter.ExpressionClient;
import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseWordChainHandler extends AbstractChainHandler {
    private static final String WORD_REGEX = "\\p{Space}\\p{Graph}+\\p{Punct}?";
    private static final String EXPRESSION_REGEX = "[\\p{Digit}\\p{Punct}]{2,}";
    private static final String PUNCT_REGEX = ".+\\p{P}]";
    private static final Logger LOG = LogManager.getLogger();

    private ArrayDeque<String> words;

    public ParseWordChainHandler() {
        words = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) throws ChainHandlerException {
        if(composite == null) {
            throw new ChainHandlerException("TextComposite object is null.");
        }
        if (textForParsing == null) {
            throw new ChainHandlerException("String object is null.");
        }

        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(textForParsing);

        while (matcher.find()) {
            String parsed = matcher.group();
            composite.add(new CharacterLeaf(TextType.CHARACTER, parsed.charAt(0)));
            parsed = parsed.trim();
            composite.add(new TextComposite(TextType.WORD));

            if (parsed.matches(PUNCT_REGEX)) {
                composite.add(new CharacterLeaf(TextType.CHARACTER, parsed.charAt(parsed.length() - 1)));
                parsed = parsed.substring(0, parsed.length() - 1);
            }

            if (parsed.matches(EXPRESSION_REGEX)) {
                PolishFormConverter converter = new PolishFormConverter();
                ExpressionClient interpreter = new ExpressionClient(converter.convert(parsed));
                parsed = interpreter.calculate().toString();
            }

            words.add(parsed);
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        try {
            handleRequest(composite, textForParsing);
            composite.getComponents()
                    .stream()
                    .filter(component -> TextType.WORD.equals(component.getType()))
                    .forEach(component -> successor.chain((TextComposite) component, words.poll()));
        } catch (ChainHandlerException e) {
            LOG.error(e);
        }
    }
}
