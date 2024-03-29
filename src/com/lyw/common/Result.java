package com.lyw.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: liuyaowen
 * @poject: WEB-ALL
 * @create: 2024-03-24 20:47
 * @Description: 响应的结果
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 构建返回结果 用于返回结果的状态码 以及对应的信息
     */
    protected static <T> Result<T> build(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    public static <T> Result<T> build(ResultCodeEnum resultCodeEnum, T data) {
        return build(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    public static <T> Result<T> success(T data) {
        return build(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

}
