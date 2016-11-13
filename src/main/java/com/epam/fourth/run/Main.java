package com.epam.fourth.run;

import com.epam.fourth.action.CompositeAction;
import com.epam.fourth.action.TextAction;
import com.epam.fourth.chains.*;
import com.epam.fourth.composite.TextComponent;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.file.FileWorker;
import com.epam.fourth.type.TextType;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {
    private static final String FILE_PATH = "data/text.txt";

    public static void main(String[] args) {
        FileWorker worker = new FileWorker();
        String str = worker.readAllFile(FILE_PATH);
        TextComposite text = new TextComposite(TextType.TEXT);

        ParseParagraphChainHandler paragraph = new ParseParagraphChainHandler();
        ParseSentenceChainHandler sentence = new ParseSentenceChainHandler();
        ParseLexemeChainHandler lexeme = new ParseLexemeChainHandler();
        ParseWordChainHandler word = new ParseWordChainHandler();
        ParseCharacterChainHandler character = new ParseCharacterChainHandler();

        paragraph.setSuccessor(sentence);
        sentence.setSuccessor(lexeme);
        lexeme.setSuccessor(word);
        word.setSuccessor(character);

        paragraph.chain(text, str);

        System.out.println("---------------------------------------------------------------");
        System.out.println(text.toString());
        System.out.println("---------------------------------------------------------------");

        /*TextAction action = new TextAction();

        //проверить метод deleteLexemes класса TextAction
        action.deleteLexemes(text, 2, 'I');
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(text.toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("################################################################");
        System.out.println(action.sortByCountOfLexeme(text));
        System.out.println("################################################################");

        System.out.println("================================================================");
        action.swapLexemes(text);
        System.out.println(text.toString());
        System.out.println("================================================================");*/
    }
}
