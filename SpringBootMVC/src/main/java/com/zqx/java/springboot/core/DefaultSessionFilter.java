package com.zqx.java.springboot.core;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Component
//@WebFilter(description = "session filter", urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "EXCLUDED", value = "/api/v1/user/login")})
public class DefaultSessionFilter implements Filter {

    private String excluded;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("session过滤器启动...");
        excluded = filterConfig.getInitParameter("EXCLUDED");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println(request.getServletContext().getContextPath());
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600); // 超时时间：1h
//        session.setAttribute("sessionId",);

    }

    @Override
    public void destroy() {

    }
}
