package com.darkdev.ecommerce.ecommerce_backend.utils;

import org.springframework.validation.BindingResult;

import java.util.List;

public class ExceptionUtils {

    public static List<String> getErrorsFromBinding(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();
    }
}
