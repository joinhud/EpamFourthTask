package com.epam.fourth.interpreter;

public class TerminalExpressionMultiply extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() * context.popValue());
    }
}
