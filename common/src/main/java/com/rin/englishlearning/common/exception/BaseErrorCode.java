package com.rin.englishlearning.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseErrorCode implements ErrorCode {
    // 🔹 Common errors
    INTERNAL_SERVER_ERROR(1000, "Lỗi máy chủ nội bộ", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST(1001, "Dữ liệu yêu cầu không hợp lệ", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1002, "Không được phép", HttpStatus.UNAUTHORIZED),
    NO_ACCESS(1003, "Không có quyền truy cập", HttpStatus.FORBIDDEN),
    RESOURCE_NOT_FOUND(1004, "Không tìm thấy tài nguyên", HttpStatus.NOT_FOUND),

    ;


    private final int code;
    private final String message;
    private final HttpStatus status;
}
