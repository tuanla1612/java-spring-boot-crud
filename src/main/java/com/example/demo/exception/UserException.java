package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class UserException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public UserException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
