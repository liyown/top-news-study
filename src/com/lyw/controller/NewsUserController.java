package com.lyw.controller;

import com.lyw.common.ResultCodeEnum;
import com.lyw.pojo.NewsUser;
import com.lyw.service.NewsUserService;
import com.lyw.utils.JwtHelper;
import com.lyw.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-28 23:00
 * @Description:
 */
@WebServlet("/user/*")
public class NewsUserController extends BaseController {
    private final NewsUserService newsUserService = new NewsUserService();

    /**
     * 登录
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readJsonToObject(req, NewsUser.class);
        assert newsUser != null;
        String token = newsUserService.login(newsUser);
        if (Objects.equals(token, "0")) {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.USERNAME_ERROR, "用户不存在");
        } else if (Objects.equals(token, "-1")) {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.PASSWORD_ERROR, "密码错误");
        } else {
            Map<String, Object> map = Map.of("token", token);
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, map);

        }
    }

    /**
     * 获取用户信息
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {

        String token = req.getHeader("token");
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.NOTLOGIN, "token已过期");
        } else {
            NewsUser newsUser = newsUserService.findUserByUid(Objects.requireNonNull(JwtHelper.getUserId(token)).intValue());
            newsUser.setUserPwd("");
            Map<String, Object> map = Map.of("loginUser", newsUser);
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, map);
        }


    }

    /**
     * 检查用户名
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        boolean userExist = newsUserService.isUserExist(new NewsUser(0, username, "", ""));
        if (userExist) {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.USERNAME_USED, "用户名已存在");
        } else {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, "用户名可用");
        }
    }

    /**
     * 注册
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) {
        NewsUser newsUser = WebUtil.readJsonToObject(req, NewsUser.class);
        assert newsUser != null;

        int register = newsUserService.register(newsUser);

        if (register == -1) {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.USERNAME_USED, "用户名已存在");
        } else if (register == 0) {
            System.out.println("注册失败");
        } else {
            System.out.println("注册成功");
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, "注册成功");
        }

    }

    /**
     * 检查登录状态
     *
     * @param req  请求
     * @param resp 响应
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) {
        String token = req.getHeader("token");
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration) {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.NOTLOGIN, "token已过期");
        } else {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.SUCCESS, "已登录");
        }
    }
}
