package com.epam.fourth.interpreter;

public class TerminalExpressionIncrement extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() + 1);
    }
}
