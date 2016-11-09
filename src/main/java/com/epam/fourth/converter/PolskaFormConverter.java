package com.epam.fourth.converter;

import java.util.ArrayDeque;

public class PolskaFormConverter {
    private static final char L_BRACKET = '(';
    private static final char R_BRACKET = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MUL = '*';
    private static final char DIV = '/';

    private static final String INCREMENT = "++";
    private static final String DECREMENT = "--";

    private static final String OPERATORS[][] = new String[][]{
            {String.valueOf(L_BRACKET), String.valueOf(R_BRACKET)},
            {String.valueOf(PLUS), String.valueOf(MINUS)},
            {String.valueOf(MUL), String.valueOf(DIV)},
            {INCREMENT, DECREMENT}
    };

    private ArrayDeque<String> stack;
    private ArrayDeque<String> out;

    public PolskaFormConverter() {
        stack = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }

    public String convert(String source) {
        char sourceChars[] = source.toCharArray();
        int i = 0;
        String result = "";

        while (i < sourceChars.length) {

            switch (sourceChars[i]) {
                case MINUS:
                    if (sourceChars[i + 1] == MINUS) {
                        workWithOperator(DECREMENT);
                        i++;
                    }
                    if (i == 0 || sourceChars[i - 1] == L_BRACKET) {
                        String num = String.valueOf(sourceChars[i]);

                        do {
                            i++;
                            num += String.valueOf(sourceChars[i]);
                        } while (Character.isDigit(sourceChars[i + 1]) && i < sourceChars.length);

                        out.push(num);
                    } else {
                        workWithOperator(String.valueOf(sourceChars[i]));
                    }
                    break;
                case PLUS:
                    if (sourceChars[i + 1] == PLUS) {
                        workWithOperator(INCREMENT);
                        i++;
                    } else {
                        workWithOperator(String.valueOf(sourceChars[i]));
                    }
                    break;
                case MUL:
                case DIV:
                    workWithOperator(String.valueOf(sourceChars[i]));
                    break;
                case R_BRACKET:
                    if (stack.size() > 0) {
                        String element = stack.pop();
                        while (!element.equals(String.valueOf(L_BRACKET))) {
                            out.push(element);
                            element = stack.pop();
                        }
                    }
                    break;
                case L_BRACKET:
                    stack.push(String.valueOf(sourceChars[i]));
                    break;
                default:
                    String num = "";

                    while (Character.isDigit(sourceChars[i])) {
                        num += String.valueOf(sourceChars[i]);
                        i++;
                        if (i == sourceChars.length) {
                            break;
                        }
                    }

                    i--;
                    out.push(num);
            }

            i++;
        }

        clearStack();

        while (out.size() > 0) {
            result += out.pollLast() + " ";
        }

        return result;
    }

    private void clearStack() {
        while (stack.size() > 0) {
            String elem = stack.pop();
            if (!elem.equals(String.valueOf(L_BRACKET))) {
                out.push(elem);
            }
        }
    }

    private void workWithOperator(String operator) {
        if (stack.size() > 0) {
            while (getPriority(operator) <= getPriority(stack.getFirst())) {
                String element = stack.pop();
                if (!element.equals(String.valueOf(L_BRACKET))) {
                    out.push(element);
                }
            }
        }

        stack.push(operator);
    }

    private int getPriority(String operator) {
        int priority = -1;

        for (int i = 0; i < OPERATORS.length; i++) {
            for (int j = 0; j < OPERATORS[i].length; j++) {
                if (OPERATORS[i][j].equals(operator)) {
                    priority = i;
                }
            }
        }

        return priority;
    }
}