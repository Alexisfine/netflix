package com.alex.exception;

public enum ExceptionType {
    INNER_ERROR(500,"Internal server error"),
    USERNAME_DUPLICATE(40001001, "Username duplicate"),
    EMAIL_DUPLICATE(40001002, "Email duplicate"),
    UNAUTHORIZED(401, "Not logged in"),
    BAD_REQUEST(400, "Request error"),
    FORBIDDEN(403, "No permission"),
    NOT_FOUND(404, "Not found"),
    USER_NOT_FOUND(40401001, "User not found");

    private final Integer code;
    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
