package com.epam.esm.handling;


public class CertificateNotFoundException extends GiftException {

    public CertificateNotFoundException() {
        super();
    }

    public CertificateNotFoundException(String message) {
        super(message);
    }

    public CertificateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CertificateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
