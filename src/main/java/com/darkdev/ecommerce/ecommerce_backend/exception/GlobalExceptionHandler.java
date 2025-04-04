package com.darkdev.ecommerce.ecommerce_backend.exception;

import com.darkdev.ecommerce.ecommerce_backend.dto.global.ApiErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleException(ApiException e) {
        return new ResponseEntity<>(
                new ApiErrorResponseDTO<>(
                        e.getPublicMessage(),
                        false,
                        Collections.singletonList(e.getMessage()),
                        null),
                e.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException e) {
        return new ResponseEntity<>(
                new ApiErrorResponseDTO<>(
                        e.getPublicMessage(),
                        false,
                        e.getErrors(),
                        null),
                e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknown(Exception e) {
        return new ResponseEntity<>(
                new ApiErrorResponseDTO<>(
                        "Unexpected error ocurred",
                        false,
                        Collections.singletonList(e.getMessage()),
                        null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
