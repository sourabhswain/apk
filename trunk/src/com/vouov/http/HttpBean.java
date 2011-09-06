package com.vouov.http;

import org.json.JSONArray;

/**
 * User: yuminglong
 * Date: 11-9-5
 * Time: 下午5:46
 * Version: 1.0.0
 */
public class HttpBean<T> {
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
