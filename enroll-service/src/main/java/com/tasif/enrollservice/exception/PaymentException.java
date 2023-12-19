package com.tasif.enrollservice.exception;

public class PaymentException extends RuntimeException {
    private String message;

    public PaymentException(String message) {
        super(message);
        this.message = message;
    }
}