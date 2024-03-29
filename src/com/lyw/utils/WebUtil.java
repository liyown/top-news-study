package com.lyw.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyw.common.Result;
import com.lyw.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: liuyaowen
 * @poject: WEB-ALL
 * @create: 2024-03-24 21:35
 * @Description:
 */
public class WebUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void writeObjectToJson(HttpServletResponse response, ResultCodeEnum resultCodeEnum, T data) {
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.build(resultCodeEnum, data)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从请求中读取json数据
     *
     * @param request 请求
     * @param clazz   类
     * @param <T>     泛型
     * @return 返回类型 T
     */
    public static <T> T readJsonToObject(HttpServletRequest request, Class<T> clazz) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return objectMapper.readValue(sb.toString(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String readJson(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
