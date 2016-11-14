package com.epam.fourth.action;

import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeException;
import com.epam.fourth.exception.TextActionException;
import com.epam.fourth.type.TextType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextActionTest {
    private static TextAction action;

    @BeforeClass
    public static void initAction() {
        action = new TextAction();
    }

    @Test(expected = CompositeException.class)
    public void sortByCountOfLexemeNullParamTest() throws CompositeException {
        String actual = null;
        actual = action.sortByCountOfLexeme(null);
        Assert.assertNull(actual);
    }

    @Test
    public void sortByCountOfLexemeCorrectParamTest() throws CompositeException {
        String actual = null;
        actual = action.sortByCountOfLexeme(new TextComposite(TextType.TEXT));
        Assert.assertNotNull(actual);
    }

    @Test(expected = CompositeException.class)
    public void swapLexemesNullParamTest() throws CompositeException {
        TextComposite actual = null;
        action.swapLexemes(actual);
    }

    @Test(expected = CompositeException.class)
    public void deleteLexemesNullCompositeTest() throws CompositeException, TextActionException {
        TextComposite actual = null;
        action.deleteLexemes(actual, 2, 'I');
    }

    @Test(expected = TextActionException.class)
    public void deleteLexemesIncorrectLengthTest() throws CompositeException, TextActionException {
        int actual = -1;
        action.deleteLexemes(new TextComposite(TextType.TEXT), actual, 'I');
    }

    @Test(expected = TextActionException.class)
    public void deleteLexemesIncorrectCharTest() throws CompositeException, TextActionException {
        char actual = '+';
        action.deleteLexemes(new TextComposite(TextType.TEXT), 2, actual);
    }
}