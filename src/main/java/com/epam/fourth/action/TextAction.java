package com.epam.fourth.action;

import com.epam.fourth.comparator.CompositeCountLexemeComparator;
import com.epam.fourth.composite.TextComponent;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeException;
import com.epam.fourth.exception.TextActionException;
import com.epam.fourth.type.TextType;

import java.util.ArrayList;
import java.util.Collections;

public class TextAction {
    private static final int FIRST_ELEMENT_INDEX = 0;
    private static final int SECOND_ELEMENT_INDEX = 1;

    public String sortByCountOfLexeme(TextComposite text) throws CompositeException {
        String result = "";
        CompositeAction action = new CompositeAction();
        ArrayList<TextComponent> sentences = action.getComponentsByType(text, TextType.SENTENCE);

        Collections.sort(sentences, new CompositeCountLexemeComparator());

        for (TextComponent sentence : sentences) {
            result += sentence.toString() + "\n";
        }

        return result;
    }

    public void swapLexemes(TextComposite text) throws CompositeException {
        CompositeAction action = new CompositeAction();
        ArrayList<TextComponent> sentences = action.getComponentsByType(text, TextType.SENTENCE);

        for (TextComponent sentence : sentences) {
            ArrayList<TextComponent> lexemes = ((TextComposite) sentence).getComponents();
            int lastElement = lexemes.size() - 1;
            Collections.swap(lexemes, FIRST_ELEMENT_INDEX, lastElement);
        }
    }

    public void deleteLexemes(TextComposite text, int length, char ch)
            throws CompositeException, TextActionException {
        CompositeAction action = new CompositeAction();
        ArrayList<TextComponent> sentences = action.getComponentsByType(text, TextType.SENTENCE);
        if (length < 1) {
            throw new TextActionException("Incorrect length.");
        }
        if (!Character.isAlphabetic(ch)) {
            throw new TextActionException("Character must be an alphabetic symbol.");
        }

        for (TextComponent sentence : sentences) {
            ArrayList<TextComponent> lexemes = action.getComponentsByType((TextComposite) sentence, TextType.LEXEME);
            for (TextComponent lexeme : lexemes) {
                String lexemeString = lexeme.toString();
                if(lexemeString.length() == length + 1 && lexemeString.charAt(SECOND_ELEMENT_INDEX) == ch) {
                    ((TextComposite) sentence).remove(lexeme);
                }
            }
        }
    }
}
