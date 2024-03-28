package com.firstproject.project.project.exception;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(LoginException.class)
    final ResponseEntity<ErrorResponse> handleUserException(LoginException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(e.getErrorCode().getErrorcode())
                .errorMessage(e.getErrorCode().getMessage())
                .errorTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("입력 형식을 확인해 주세요.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.toString())
                .errorMessage(ex.getBindingResult().getFieldError().getDefaultMessage())
                .errorTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<String> unexpectedTypeException(UnexpectedTypeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("공백은 입력할 수 없습니다.");
    }
}
