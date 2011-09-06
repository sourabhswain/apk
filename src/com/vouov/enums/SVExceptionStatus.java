package com.vouov.enums;

/**
 * User: yuminglong
 * Date: 11-9-5
 * Time: 下午1:25
 * Version: 1.0.0
 */
public enum SVExceptionStatus {
    FAILED(400,"Fail"), UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(402, "Forbidden"), REQUEST_FAILED(403, "Request Failed");
    private int code;
    private String description;

    SVExceptionStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
