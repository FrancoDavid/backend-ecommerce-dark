package com.darkdev.ecommerce.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ValidationException extends ApiException {
    private final List<String> errors;

    public ValidationException(String publicMessage, List<String> errors) {
        super(publicMessage, String.join(", ", errors), HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
