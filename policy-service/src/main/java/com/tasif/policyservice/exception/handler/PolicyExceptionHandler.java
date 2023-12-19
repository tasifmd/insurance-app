package com.tasif.policyservice.exception.handler;

import com.tasif.policyservice.exception.PolicyAlreadyExistsException;
import com.tasif.policyservice.exception.PolicyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PolicyExceptionHandler {

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<?> policyNotFoundExceptionHandler(PolicyNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PolicyAlreadyExistsException.class)
    public ResponseEntity<?> policyAlreadyExistExceptionHandler(PolicyAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
