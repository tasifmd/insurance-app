package com.tasif.enrollservice.exception;

public class UserAlreadyEnrolledException extends RuntimeException {
    private String message;

    public UserAlreadyEnrolledException(String message) {
        super(message);
        this.message = message;
    }
}
