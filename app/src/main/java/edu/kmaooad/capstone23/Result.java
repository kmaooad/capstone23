package edu.kmaooad.capstone23;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class Result<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public Result(T resultValue) {
        this.value = resultValue;
    }

    public Result(String error) {
        this.success = false;
        this.message = error;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

}