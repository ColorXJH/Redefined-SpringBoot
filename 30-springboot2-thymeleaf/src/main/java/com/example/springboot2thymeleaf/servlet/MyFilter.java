package com.example.springboot2thymeleaf.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/29 15:08
 */
//@WebFilter(urlPatterns = {"/static/css/*","/static/images/*"})//注意，/*是servlet写法，/**是spring写法
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("my-filter初始化完成");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("my-filter开始工作");
        //链 放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("my-filter销毁完成");
    }
}
