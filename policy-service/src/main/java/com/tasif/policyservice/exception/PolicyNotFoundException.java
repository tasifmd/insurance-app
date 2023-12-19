package com.tasif.policyservice.exception;

public class PolicyNotFoundException extends RuntimeException {
    private String message;

    public PolicyNotFoundException(String message) {
        super(message);
        this.message = message;
    }


}
