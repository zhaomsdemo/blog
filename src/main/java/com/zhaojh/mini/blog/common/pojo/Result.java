package com.zhaojh.mini.blog.common.pojo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result<T> {

    int code;
    int total;
    String message;
    T data;

    public static <T> Result<T> success(T data) {
        return Result.<T>builder().code(200).message("success").data(data).build();
    }

    public static <T> Result<T> success(T data, String message) {
        return Result.<T>builder().code(200).message(message).data(data).build();
    }

    public static <T> Result<T> success(T data, int total) {
        return Result.<T>builder().code(200).message("success").data(data).total(total).build();
    }

    public static <T> Result<T> fail(String message) {
        return Result.<T>builder().code(500).message(message).build();
    }

    public static <T> Result<T> fail(int code, String message) {
        return Result.<T>builder().code(code).message(message).build();
    }

    public static <T> Result<T> fail(int code, String message, T data) {
        return Result.<T>builder().code(code).message(message).data(data).build();
    }

    public static <T> Result<T> fail(T data) {
        return Result.<T>builder().code(500).message("fail").data(data).build();
    }
}
