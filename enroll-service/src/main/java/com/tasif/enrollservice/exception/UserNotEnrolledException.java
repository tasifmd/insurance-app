package com.tasif.enrollservice.exception;

public class UserNotEnrolledException extends RuntimeException {
    private String message;

    public UserNotEnrolledException(String message) {
        super(message);
        this.message = message;
    }
}
