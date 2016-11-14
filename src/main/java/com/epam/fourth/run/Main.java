package com.epam.fourth.run;

import com.epam.fourth.action.TextAction;
import com.epam.fourth.chains.*;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeException;
import com.epam.fourth.exception.TextActionException;
import com.epam.fourth.file.FileWorker;
import com.epam.fourth.report.Report;
import com.epam.fourth.type.TextType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final String FILE_PATH = "data/text.txt";
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        FileWorker worker = new FileWorker();
        String str = worker.readAllFile(FILE_PATH);
        TextComposite text = new TextComposite(TextType.TEXT);
        Report report = new Report();
        TextAction action = new TextAction();

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

        try {
            report.writeTextCompositeDataReport(text);
            report.writeSortedTextCompositReport(action.sortByCountOfLexeme(text));
            action.swapLexemes(text);
            report.writeTextCompositeDataReport(text);
            action.deleteLexemes(text, 2, 'I');
            report.writeTextCompositeDataReport(text);
        } catch (CompositeException | TextActionException e) {
            LOG.log(Level.ERROR, e);
        }
    }
}
