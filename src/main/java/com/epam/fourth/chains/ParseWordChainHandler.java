package com.epam.fourth.chains;

import com.epam.fourth.composite.CharacterLeaf;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.type.TextType;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseWordChainHandler extends AbstractChainHandler {
    private static final String WORD_REGEX = "\\p{Space}[\\p{Alnum}-+(]+\\p{P}?";
    private static final String PUNCT_REGEX = ".+\\p{P}]";
    private ArrayDeque<String> words;

    public ParseWordChainHandler() {
        words = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) {
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(textForParsing);
        while (matcher.find()) {
            String parsed = matcher.group();
            composite.add(new CharacterLeaf(TextType.CHARACTER, parsed.charAt(0)));
            composite.add(new TextComposite(TextType.WORD));
            if (parsed.matches(PUNCT_REGEX)) {
                composite.add(new CharacterLeaf(TextType.CHARACTER, parsed.charAt(parsed.length() - 1)));
            }
            words.add(parsed);
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        handleRequest(composite, textForParsing);
        composite.getComponents()
                .stream()
                .filter(component -> component.getType().equals(TextType.WORD))
                .forEach(component -> successor.chain((TextComposite) component, words.poll()));
    }
}
