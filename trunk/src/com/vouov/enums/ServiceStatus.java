package com.vouov.enums;

/**
 * 远程服务返回的状态码
 * User: yuminglong
 * Date: 11-9-5
 * Time: 上午11:41
 * Version: 1.0.0
 */
public enum ServiceStatus {
    SUCCESS(200, "Success"), FAIL(400,"Fail"), UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(402, "Forbidden"), EXPECTATION_FAILED(403, "Expectation failed");

    private int code;
    private String description;
    ServiceStatus(int code, String description) {
       this.code = code;
        this.description = description;
    }
}
