package com.alex.exception;

public enum ExceptionType {
    INNER_ERROR(500,"Internal server error"),

    UNAUTHORIZED(401, "Not logged in"),
    BAD_REQUEST(400, "Request error"),
    FORBIDDEN(403, "No permission"),
    NOT_FOUND(404, "Not found"),
    USERNAME_DUPLICATE(40001001, "Username duplicate"),
    EMAIL_DUPLICATE(40001002, "Email duplicate"),
    FILE_NOT_FOUND(40001003, "File not found"),
    ILLEGAL_FILE_TYPE(40001004, "Illegal file type"),
    UNABLE_TO_WRITE_FILE(40001005, "Unable to write file"),

    USER_NOT_FOUND(40401001, "User not found"),
    MOVIE_NOT_FOUND(40402001, "Movie not found"),

    USER_PASSWORD_NOT_MATCH(40001003, "Username or password is incorrect"),
    USER_NOT_ENABLED(50001001, "User not enabled"),
    USER_LOCKED(50001002, "User is locked"),
    FAILED_TO_DOWNLOAD(50001003, "Failed to download");

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
