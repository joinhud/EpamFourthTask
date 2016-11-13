package com.epam.fourth.interpreter;

public class TerminalExpressionPlus extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        if (context != null) {
            context.pushValue(context.popValue() + context.popValue());
        }
    }
}
