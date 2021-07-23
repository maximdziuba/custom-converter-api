package com.converter.exception;

public class EmptyCurrencyFieldException extends RuntimeException {
    public EmptyCurrencyFieldException(String message) {
        super(message);
    }
}
