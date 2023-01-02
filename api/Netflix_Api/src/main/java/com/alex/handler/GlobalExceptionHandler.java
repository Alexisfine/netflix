package com.alex.handler;

import com.alex.exception.BizException;
import com.alex.exception.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

import static com.alex.exception.ExceptionType.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BizException.class)
    public ErrorResponse bizExceptionHandler(BizException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ex.getCode());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTrace(ex.getStackTrace());
        return errorResponse;
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorResponse ExceptionHandler(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(INNER_ERROR.getCode());
        errorResponse.setMessage(INNER_ERROR.getMessage());
        errorResponse.setTrace(ex.getStackTrace());
        return errorResponse;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ErrorResponse AccessDeniedExceptionHandler(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(FORBIDDEN.getCode());
        errorResponse.setMessage(FORBIDDEN.getMessage());
        errorResponse.setTrace(ex.getStackTrace());
        return errorResponse;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        ex
                .getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    errorResponse.setCode(BAD_REQUEST.getCode());
                    errorResponse.setMessage(error.getDefaultMessage());
                });
        return errorResponse;
    }
}
