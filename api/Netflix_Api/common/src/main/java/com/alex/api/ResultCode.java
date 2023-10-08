package com.alex.api;

public enum ResultCode implements IErrorCode{
    SUCCESS(200L, "SUCCESS"),
    FAILED(500L, "INTERNAL_SERVER_ERROR"),
    VALIDATE_FAILED(404L, "PARAMS_VALIDATION_FAILED"),
    UNAUTHORIZED(401L, "UNAUTHORIZED"),
    FORBIDDEN(403L, "FORBIDDEN");
    private long code;
    private String message;

    ResultCode(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Long getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
