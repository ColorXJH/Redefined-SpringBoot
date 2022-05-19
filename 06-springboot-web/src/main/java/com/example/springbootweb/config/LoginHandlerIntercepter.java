package com.example.springbootweb.config;

import com.example.springbootweb.exception.LoginOutException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginHandlerIntercepter
 * @Package: com.example.springbootweb.config
 * @Description: 检查登录
 * @Datetime: 2022/5/19 16:07
 * @author: ColorXJH
 */
//注意，拦截器写完之后一定要配置出来，在配置类中完成相应操作
public class LoginHandlerIntercepter implements HandlerInterceptor {
    //alt+insert 重写接口方法
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user=request.getSession().getAttribute("loginUser");
        if(user==null){
            //1未登录，返回登录页面
            request.setAttribute("msg","没有权限请登录");
            String url=request.getRequestURI();
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;

            //2这种重定向的方法失去了携带参数的方法并且需要规定好url地址
            //response.sendRedirect("/crud/");
            //return false;

            //3使用异常处理方法，抛出异常并捕捉然后处理异常重定向到登录页面
            //throw new LoginOutException("没有权限请登录");
        }else{
            //已登录
            return true;
        }

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
