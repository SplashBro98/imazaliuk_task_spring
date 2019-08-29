package com.epam.esm.handling;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private String httpStatus;
    private String message;

    public ErrorMessage() {

    }

    public ErrorMessage(HttpStatus status, String message) {
        this.httpStatus = status.toString();
        this.message = message;
    }

    public String getStatus() {
        return httpStatus;
    }

    public void setStatus(HttpStatus status) {
        this.httpStatus = status.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
