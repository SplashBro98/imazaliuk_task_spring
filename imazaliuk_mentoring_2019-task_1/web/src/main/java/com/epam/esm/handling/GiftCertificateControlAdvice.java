package com.epam.esm.handling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GiftCertificateControlAdvice {

    private static Logger logger = LoggerFactory.getLogger(GiftCertificateControlAdvice.class);

    @ExceptionHandler(CertificateNotFoundException.class)
    public ResponseEntity<ErrorMessage> handle(CertificateNotFoundException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.NO_CONTENT;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);

    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ErrorMessage> handle(TagNotFoundException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.NO_CONTENT;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handle(IllegalArgumentException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handle(HttpMessageNotReadableException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);

    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorMessage> handle(NumberFormatException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorMessage> handle(NoHandlerFoundException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handle(MethodArgumentNotValidException e) {
        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorMessage> handle(ServiceException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handle(RuntimeException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(PoolCreationException.class)
    public ResponseEntity<ErrorMessage> handle(PoolCreationException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handle(HttpRequestMethodNotSupportedException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handle(HttpMediaTypeNotSupportedException e) {

        logger.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = new ErrorMessage(status, e.getLocalizedMessage());
        return new ResponseEntity<>(message, status);
    }

}
