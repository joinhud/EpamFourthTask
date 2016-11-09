package com.epam.fourth.chains;

import com.epam.fourth.composite.CharacterLeaf;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.type.TextType;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseParagraphChainHandler extends AbstractChainHandler {
    private static final String PARAGRAPH_REGEX = "\\p{Blank}+.+\n";
    private ArrayDeque<String> paragraphs;

    public ParseParagraphChainHandler() {
        paragraphs = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) {
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
        handleRequest(composite, textForParsing);
        composite.getComponents()
                .stream()
                .filter(component -> component.getType().equals(TextType.PARAGRAPH))
                .forEach(component -> successor.chain((TextComposite) component, paragraphs.poll()));
    }
}
