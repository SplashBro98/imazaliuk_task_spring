package com.epam.esm.handling;

public class GiftException extends RuntimeException {

    public GiftException() {
        super();
    }

    public GiftException(String message) {
        super(message);
    }

    public GiftException(String message, Throwable cause) {
        super(message, cause);
    }

    public GiftException(Throwable cause) {
        super(cause);
    }

    protected GiftException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
