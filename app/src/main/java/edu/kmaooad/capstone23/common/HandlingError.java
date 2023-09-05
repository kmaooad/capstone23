package edu.kmaooad.capstone23.common;

public class HandlingError {
    private String message;
    private ErrorCode code;

    public HandlingError(ErrorCode code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getCode() {
        return code;
    }
}