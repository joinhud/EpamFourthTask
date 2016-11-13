package com.epam.fourth.interpreter;

public class TerminalExpressionDecrement extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        if (context != null) {
            context.pushValue(context.popValue() - 1);
        }
    }
}
