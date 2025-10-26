package com.rin.toeic.common.exception;

import com.rin.toeic.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e) {
        BaseErrorCode baseErrorCode = BaseErrorCode.INTERNAL_SERVER_ERROR;
        System.err.println("Unhandled exception: " + e.getMessage());
        return ResponseEntity.status(baseErrorCode.getStatus()).body(ApiResponse.builder()
                .code(baseErrorCode.getCode())
                .message(e.getMessage())
                .build());
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse> handleBaseException(BaseException ex) {
        var baseErrorCode = ex.getErrorCode();
        return ResponseEntity
                .status(baseErrorCode.getStatus())
                .body(ApiResponse.builder()
                        .code(baseErrorCode.getCode())
                        .message(ex.getMessage())
                        .build());
    }
}
