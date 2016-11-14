package com.epam.fourth.action;

import com.epam.fourth.composite.TextComponent;
import com.epam.fourth.composite.TextComposite;
import com.epam.fourth.exception.CompositeException;
import com.epam.fourth.type.TextType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class CompositeActionTest {
    private static CompositeAction action;

    @BeforeClass
    public static void initAction() {
        action = new CompositeAction();
    }

    @Test(expected = CompositeException.class)
    public void getComponentsByTypeNullCompositeTest() throws CompositeException {
        ArrayList<TextComponent> actual = null;
        actual = action.getComponentsByType(null, TextType.LEXEME);
        Assert.assertNull(actual);
    }

    @Test(expected = CompositeException.class)
    public void getComponentsByTypeNullTypeTest() throws CompositeException {
        ArrayList<TextComponent> actual = null;
        actual = action.getComponentsByType(new TextComposite(TextType.TEXT), null);
        Assert.assertNull(actual);
    }

    @Test(expected = CompositeException.class)
    public void getComponentsByTypeNullParamsTest() throws CompositeException {
        ArrayList<TextComponent> actual = null;
        actual = action.getComponentsByType(null, null);
        Assert.assertNull(actual);
    }

    @Test
    public void getComponentsByTypeCorrectTest() throws CompositeException {
        ArrayList<TextComponent> actual = null;
        actual = action.getComponentsByType(new TextComposite(TextType.TEXT), TextType.LEXEME);
        Assert.assertNotNull(actual);
    }

    @Test(expected = CompositeException.class)
    public void calculateLexemesCountNullParamTest() throws CompositeException {
        int actual;
        actual = action.calculateLexemesCount(null);
        Assert.assertEquals(0, actual);
    }

    @Test
    public void calculateLexemesCountCorrectParamTest() throws CompositeException {
        int actual = action.calculateLexemesCount(new TextComposite(TextType.TEXT));
        Assert.assertEquals(0, actual);
    }
}