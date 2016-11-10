package com.epam.fourth.interpreter;

public class NonterminalExpressionNumber extends AbstractMathExpression {
    private double number;

    public NonterminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
