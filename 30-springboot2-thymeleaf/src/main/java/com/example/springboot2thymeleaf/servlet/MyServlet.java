package com.example.springboot2thymeleaf.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 自定义servlet
 * @date 2022/9/29 15:02
 */
//@WebServlet(urlPatterns = "/my")
//直接响应，没有spring的拦截，
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("666666");
    }
}
