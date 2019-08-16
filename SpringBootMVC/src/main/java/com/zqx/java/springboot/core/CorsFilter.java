package com.zqx.java.springboot.core;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("CorsFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 响应类型
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");

        // 响应头设置
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, HaiYi-Access-Token");

        // 预请求缓存时间(秒)
        response.addHeader("Access-Control-Max-Age", "18000");

        // 请求查询服务器的性能，或者查询与资源相关的选项和需求
        if ("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }

        chain.doFilter(servletRequest, servletResponse);
    }

}
