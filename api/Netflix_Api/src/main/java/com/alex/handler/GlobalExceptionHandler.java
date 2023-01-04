package com.alex.handler;

import com.alex.exception.BizException;
import com.alex.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.alex.exception.ExceptionType.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse bizExceptionHandler(BizException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ex.getCode());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTrace(ex.getStackTrace());

        ex.printStackTrace();
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse exceptionHandler(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(INNER_ERROR.getCode());
        errorResponse.setMessage(INNER_ERROR.getMessage());
        errorResponse.setTrace(ex.getStackTrace());

        ex.printStackTrace();

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = org.springframework.security.access.AccessDeniedException.class)
    public ErrorResponse accessDeniedExceptionHandler(AccessDeniedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(FORBIDDEN.getCode());
        errorResponse.setMessage(FORBIDDEN.getMessage());
        errorResponse.setTrace(ex.getStackTrace());
        ex.printStackTrace();

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public List<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        List<ErrorResponse> errorResponses = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorResponse.setCode(BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponses.add(errorResponse);
        });

        ex.printStackTrace();
        return errorResponses;
    }
}
