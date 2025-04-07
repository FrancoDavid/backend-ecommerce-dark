package com.darkdev.ecommerce.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends  ApiException {
    public NotFoundException(String publicMessage, String internalMessage) {
        super(publicMessage, internalMessage, HttpStatus.NOT_FOUND);
    }
}
