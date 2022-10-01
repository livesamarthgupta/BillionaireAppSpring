package com.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorBoundry {

    @ExceptionHandler
    public ResponseEntity<?> billionaireNotFound(BillionaireNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<String> forbesServiceIssue(InitializationFailedException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue with forbes 400 service.");
    }
}
