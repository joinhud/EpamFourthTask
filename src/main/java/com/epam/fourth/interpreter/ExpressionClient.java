package com.epam.fourth.interpreter;

import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class ExpressionClient {
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";
    private static final String INCREMENT = "++";
    private static final String DECREMENT = "--";

    private AbstractList<AbstractMathExpression> listExpression;

    public ExpressionClient(ArrayDeque<String> expression) {
        listExpression = new ArrayList<>();
        if (expression != null) {
            parse(expression);
        }
    }

    private void parse(ArrayDeque<String> expression) {
        while (!expression.isEmpty()) {
            String lexeme = expression.pollLast();

            if (lexeme.isEmpty()) {
                continue;
            }

            switch (lexeme) {
                case PLUS:
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case MINUS:
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case MULTIPLY:
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case DIVIDE:
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case INCREMENT:
                    listExpression.add(new TerminalExpressionIncrement());
                    break;
                case DECREMENT:
                    listExpression.add(new TerminalExpressionDecrement());
                    break;
                default:
                    listExpression.add(new NonterminalExpressionNumber(Double.parseDouble(lexeme)));
            }
        }
    }

    public Number calculate() {
        if (listExpression.isEmpty()) {
            return null;
        }

        Context context = new Context();

        for (AbstractMathExpression terminal : listExpression) {
            terminal.interpret(context);
        }

        return context.popValue().intValue();
    }
}
