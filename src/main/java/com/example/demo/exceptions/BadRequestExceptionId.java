package com.example.demo.exceptions;

public class BadRequestExceptionId extends RuntimeException {
    public BadRequestExceptionId(String message) {
        super(message);
    }
}