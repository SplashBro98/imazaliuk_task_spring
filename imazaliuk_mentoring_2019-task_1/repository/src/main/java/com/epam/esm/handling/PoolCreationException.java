package com.epam.esm.handling;

public class PoolCreationException extends RepositoryException {

    public PoolCreationException() {
        super();
    }

    public PoolCreationException(String message) {
        super(message);
    }

    public PoolCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolCreationException(Throwable cause) {
        super(cause);
    }

    protected PoolCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
