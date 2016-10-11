package com.epam.third.exception;

public class DockPoolException extends Exception {
    public DockPoolException() {
        super();
    }

    public DockPoolException(String message) {
        super(message);
    }

    public DockPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DockPoolException(Throwable cause) {
        super(cause);
    }

    protected DockPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
