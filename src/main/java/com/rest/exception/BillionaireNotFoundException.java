package com.rest.exception;

public class BillionaireNotFoundException extends RuntimeException {
    public BillionaireNotFoundException() {
        super("404: Billionaire not found.");
    }
}
