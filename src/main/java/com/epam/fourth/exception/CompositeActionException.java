package com.epam.fourth.exception;

public class CompositeActionException extends Exception {
    public CompositeActionException() {
        super();
    }

    public CompositeActionException(String message) {
        super(message);
    }

    public CompositeActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompositeActionException(Throwable cause) {
        super(cause);
    }

    protected CompositeActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
