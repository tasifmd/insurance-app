package com.tasif.policyservice.exception;

public class PolicyAlreadyExistsException extends RuntimeException {
    private String message;

    public PolicyAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

