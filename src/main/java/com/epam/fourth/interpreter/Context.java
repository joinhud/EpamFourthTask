package com.epam.fourth.interpreter;

import java.util.ArrayDeque;

public class Context {
    private ArrayDeque<Double> contextValue = new ArrayDeque<>();

    public Double popValue() {
        return contextValue.pop();
    }

    public void pushValue(Double value) {
        this.contextValue.push(value);
    }
}
