package com.epam.fourth.exception;

public class TextActionException extends Exception {
    public TextActionException() {
        super();
    }

    public TextActionException(String message) {
        super(message);
    }

    public TextActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextActionException(Throwable cause) {
        super(cause);
    }

    protected TextActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
