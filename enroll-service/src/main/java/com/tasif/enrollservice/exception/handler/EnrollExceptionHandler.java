package com.tasif.enrollservice.exception.handler;

import com.tasif.enrollservice.exception.PaymentException;
import com.tasif.enrollservice.exception.UserAlreadyEnrolledException;
import com.tasif.enrollservice.exception.UserNotEnrolledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EnrollExceptionHandler {

    @ExceptionHandler(UserAlreadyEnrolledException.class)
    public ResponseEntity<String> userAlreadyEnrolledExceptionHandler(UserAlreadyEnrolledException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotEnrolledException.class)
    public ResponseEntity<String> userNotFoundExceptionHandler(UserNotEnrolledException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<String> paymentExceptionHandler(PaymentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
