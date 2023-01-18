package com.example.embedika_test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiErrorResponse> entityNotFound(EntityNotFoundException ex) {

        ApiErrorResponse apiResponse = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withErrorCode("404")
                .withMessage(ex.getMessage())
                .withDetails("Невалидный id")
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<ApiErrorResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiErrorResponse> handleRestRuntimeException(RuntimeException ex) {

        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withErrorCode("500")
                .withMessage(ex.getMessage())
                .withDetails("Попробуйте позже.")
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<ApiErrorResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiErrorResponse> handleDefaultException(Exception ex) {

        ApiErrorResponse apiResponse = new ApiErrorResponse
                .ApiErrorResponseBuilder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .withErrorCode("503")
                .withMessage(ex.getLocalizedMessage())
                .withDetails("Что-то пошло не так.")
                .atTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return new ResponseEntity<ApiErrorResponse>(apiResponse, apiResponse.getHttpStatus());
    }
}
