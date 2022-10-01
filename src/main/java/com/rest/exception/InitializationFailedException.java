package com.rest.exception;

public class InitializationFailedException extends RuntimeException {
    public InitializationFailedException() {
        super("Issue with forbes 400 service.");
    }
}
