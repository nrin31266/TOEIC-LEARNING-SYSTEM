package com.rin.englishlearning.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    int getCode();
    String getMessage();
    HttpStatus getStatus();

    /**
     * Phương thức default, được implement sẵn.
     * Các enum sẽ tự động có hàm này.
     */
    default String formatMessage(Object... args) {
        // 'getMessage()' sẽ gọi đến hàm getMessage()
        // của enum cụ thể (ví dụ: ToeicErrorCode)
        return String.format(this.getMessage(), args);
    }
}
