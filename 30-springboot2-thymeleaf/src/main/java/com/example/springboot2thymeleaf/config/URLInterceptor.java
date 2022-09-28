package com.example.springboot2thymeleaf.config;

import com.example.springboot2thymeleaf.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/27 16:56
 */
@Component
@Slf4j
public class URLInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("loginUser")!=null){
            User user=(User)request.getSession().getAttribute("loginUser");
            if(user!=null&&user.getUserName().equals("color")){
                return true;
            }
        }
        log.info("用户未登录，返回登录页面！");
        request.setAttribute("msg","用户未登录，返回登录页面！");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
