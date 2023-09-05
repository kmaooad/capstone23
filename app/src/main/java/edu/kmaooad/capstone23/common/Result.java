package edu.kmaooad.capstone23.common;

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
        this.success = true;
    }

    public Result(ErrorCode code, String error) {
        this.success = false;
        this.message = error;
        this.errorCode = code;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
        this.success = false;
        this.errorCode = ErrorCode.VALIDATION_FAILED;
        this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    private String message;
    private boolean success;
    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public HandlingError toError() {
        return new HandlingError(errorCode, message);
    }

}