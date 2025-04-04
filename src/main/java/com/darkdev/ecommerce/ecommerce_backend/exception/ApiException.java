package com.darkdev.ecommerce.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final String publicMessage;
    private final HttpStatus status;

    public ApiException(String publicMessage, String internalMessage, HttpStatus status) {
        super(internalMessage);
        this.publicMessage = publicMessage;
        this.status = status;
    }

    public String getPublicMessage() {
        return publicMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
