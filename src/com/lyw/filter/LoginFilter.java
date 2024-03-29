package com.lyw.filter;

import com.lyw.common.ResultCodeEnum;
import com.lyw.utils.JwtHelper;
import com.lyw.utils.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author: liuyaowen
 * @poject: TopNews
 * @create: 2024-03-29 19:01
 * @Description:
 */
@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String token = req.getHeader("token");
        if (token != null && !JwtHelper.isExpiration(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            WebUtil.writeObjectToJson(resp, ResultCodeEnum.NOTLOGIN, "token已过期");
        }
    }
}
