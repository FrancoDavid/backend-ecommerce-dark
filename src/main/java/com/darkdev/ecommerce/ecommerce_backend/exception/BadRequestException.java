package com.darkdev.ecommerce.ecommerce_backend.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends  ApiException {
    public BadRequestException(String publicMessage, String internalMessage) {
        super(publicMessage, internalMessage, HttpStatus.BAD_REQUEST);
    }
 }
