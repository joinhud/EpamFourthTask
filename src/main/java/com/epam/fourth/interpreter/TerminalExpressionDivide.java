package com.epam.fourth.interpreter;

public class TerminalExpressionDivide extends AbstractMathExpression {
    @Override
    public void interpret(Context context) {
        Double secondOperand = context.popValue();
        Double firstOperand = context.popValue();
        context.pushValue(firstOperand / secondOperand);
    }
}
