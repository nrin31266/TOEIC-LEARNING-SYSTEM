package com.rin.toeic.exception;

import com.rin.toeic.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        System.err.println("Unhandled exception: " + e.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(e.getMessage())
                .build());
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse> handleBaseException(BaseException ex) {
        var errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(ex.getMessage())
                        .build());
    }
}
