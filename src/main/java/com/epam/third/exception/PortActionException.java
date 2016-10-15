package com.epam.third.exception;

public class PortActionException extends Exception {
    public PortActionException() {
        super();
    }

    public PortActionException(String message) {
        super(message);
    }

    public PortActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortActionException(Throwable cause) {
        super(cause);
    }

    protected PortActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
