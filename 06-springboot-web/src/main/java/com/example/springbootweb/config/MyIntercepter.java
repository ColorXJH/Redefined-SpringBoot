package com.example.springbootweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @ClassName: MyIntercepter
 * @Package: com.example.springbootweb.config
 * @Description:
 * @Datetime: 2022/2/22 22:43
 * @author: ColorXJH
 */
@Component
public class MyIntercepter implements HandlerInterceptor {
    @Override
    @ResponseBody
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        String link = "<script>" +
                "var link = document.createElement('link');" +
                "link.type = 'image/x-icon';" +
                "link.rel = 'shortcut icon';" +
                "link.href = '/crud/favicon.ico';" +
                "document.getElementsByTagName('head')[0].appendChild(link);" +
                "</script>";
        response.getWriter().append(link);
    }
}
