package com.epam.fourth.interpreter;

import com.epam.fourth.converter.PolishFormConverter;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionClientTest {

    @Test
    public void calculateNullExpressionTest() {
        ExpressionClient client = new ExpressionClient(null);
        Number actual = client.calculate();
        Assert.assertNull(actual);
    }

    @Test
    public void calculateCorrectExpressionTest() {
        String expression = "(-5+1/2*(2+5*2))*1200";
        PolishFormConverter converter = new PolishFormConverter();
        ExpressionClient client = new ExpressionClient(converter.convert(expression));
        String actual = client.calculate().toString();
        String expected = "1200";
        Assert.assertEquals(expected, actual);
    }
}