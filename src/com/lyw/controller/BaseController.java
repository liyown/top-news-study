package com.lyw.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author: liuyaowen
 * @poject: WEB-ALL
 * @create: 2024-03-23 18:32
 * @Description:
 */
public class BaseController extends HttpServlet {
    /**
     * 重写service方法
     *
     * @param req  请求
     * @param resp 响应
     * @throws ServletException 异常
     * @throws IOException      异常
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 判断业务
        String requestURI = req.getRequestURI();
        // 获取方法名
        String[] strings = requestURI.split("/");
        String method = strings[strings.length - 1];
        // 获取当前类
        Class<? extends BaseController> baseController = this.getClass();
        try {
            // 获取方法
            Method declaredMethod = baseController.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
