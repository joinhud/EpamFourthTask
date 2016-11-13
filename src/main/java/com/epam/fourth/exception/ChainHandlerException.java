package com.epam.fourth.exception;

public class ChainHandlerException extends Exception {
    public ChainHandlerException() {
        super();
    }

    public ChainHandlerException(String message) {
        super(message);
    }

    public ChainHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChainHandlerException(Throwable cause) {
        super(cause);
    }

    protected ChainHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
