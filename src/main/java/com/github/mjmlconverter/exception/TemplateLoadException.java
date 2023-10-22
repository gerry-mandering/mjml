package com.github.mjmlconverter.exception;

public class TemplateLoadException extends RuntimeException {

    public TemplateLoadException(String message) {
        super(message);
    }

    public TemplateLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
