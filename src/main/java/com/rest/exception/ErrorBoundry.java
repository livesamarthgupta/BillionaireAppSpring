package com.rest.exception;

import org.springframework.dao.EmptyResultDataAccessException;
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
    public ResponseEntity<?> jdbcBillionaireNotFound(EmptyResultDataAccessException exception) {
        return ResponseEntity.notFound().build();
    }
}
