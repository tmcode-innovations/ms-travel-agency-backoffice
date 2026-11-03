package dev.mm.javaee.core.rest;

import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {

    public static <T> ApiResponse<T> build(ResponseEntity<T> response) {
        return new ApiResponse<>(
                String.valueOf(response.getStatusCode().value()),
                response.getStatusCode().toString(),
                response.getBody()
        );
    }

    public static <T> ApiResponse<T> build(String status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> success() {
        return ApiResponse.success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("200", "Success", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("200", message, data);
    }

    private String status;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String status, String msg, T data) {
        this.status = status;
        this.message = msg;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
