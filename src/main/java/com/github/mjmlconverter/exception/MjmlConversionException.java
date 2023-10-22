package com.github.mjmlconverter.exception;

public class MjmlConversionException extends RuntimeException{

    public MjmlConversionException(String message) {
        super(message);
    }

    public MjmlConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
