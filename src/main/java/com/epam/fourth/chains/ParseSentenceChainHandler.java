package com.epam.fourth.chains;

import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.type.TextType;

import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseSentenceChainHandler extends AbstractChainHandler {
    private static final String SENTENCE_REGEX = "\\p{Space}\\p{Upper}([^.!?]+)[.!?]+";
    private ArrayDeque<String> sentences;

    public ParseSentenceChainHandler() {
        sentences = new ArrayDeque<>();
    }

    @Override
    public void handleRequest(TextComposite composite, String textForParsing) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(textForParsing);
        while (matcher.find()) {
            composite.add(new TextComposite(TextType.SENTENCE));
            sentences.add(matcher.group());
        }
    }

    @Override
    public void chain(TextComposite composite, String textForParsing) {
        handleRequest(composite, textForParsing);
        composite.getComponents()
                .stream()
                .filter(component -> component.getType().equals(TextType.SENTENCE))
                .forEach(component -> successor.chain((TextComposite) component, sentences.poll()));
    }
}
