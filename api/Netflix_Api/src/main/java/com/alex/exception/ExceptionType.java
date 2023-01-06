package com.alex.exception;

public enum ExceptionType {
    INNER_ERROR(500,"Internal server error"),

    UNAUTHORIZED(401, "Not logged in"),
    BAD_REQUEST(400, "Request error"),
    FORBIDDEN(403, "No permission"),
    NOT_FOUND(404, "Not found"),
    USERNAME_DUPLICATE(40001001, "Username duplicate"),
    EMAIL_DUPLICATE(40001002, "Email duplicate"),
    USER_PASSWORD_NOT_MATCH(40001003, "Username or password is incorrect"),
    FILE_NOT_FOUND(40001004, "File not found"),
    ILLEGAL_FILE_TYPE(40001005, "Illegal file type"),
    UNABLE_TO_WRITE_FILE(40001006, "Unable to write file"),
    INVALID_EMAIL_FORMAT(40001007, "Invalid email format"),

    VERIFICATION_CODE_EXPIRED(40001008, "Verification code expired"),
    VERIFICATION_CODE_INCORRECT(40001009, "Verification code incorrect"),

    USERNAME_EMAIL_MISMATCH(40001010, "Username or email incorrect"),
    INVALID_PHONE_NUMBER(40001011, "Invalid phone number"),
    LIST_TITLE_DUPLiCATE(40003001, "List title duplicate"),
    USER_NOT_FOUND(40401001, "User not found"),
    MOVIE_NOT_FOUND(40402001, "Movie not found"),
    LIST_NOT_FOUND(40402001, "List not found"),

    USER_NOT_ENABLED(50001001, "User not enabled"),
    USER_LOCKED(50001002, "User is locked"),
    FAILED_TO_DOWNLOAD(50001003, "Failed to download"),
    EMAIL_SENDING_ERROR(50001004, "Failed to send email");


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
