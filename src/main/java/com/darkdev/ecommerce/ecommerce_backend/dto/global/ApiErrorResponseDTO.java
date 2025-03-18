package com.darkdev.ecommerce.ecommerce_backend.dto.global;

import org.springframework.http.HttpStatusCode;

import java.util.List;

public class ApiErrorResponseDTO<T> {
    public Boolean success;
    public String message;
    public List<T> errors;
    public T data;

    public ApiErrorResponseDTO(String message, Boolean success, List<T> errors, T data) {
        this.message = message;
        this.success = success;
        this.errors = errors;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<T> getErrors() {
        return errors;
    }

    public void setErrors(List<T> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
