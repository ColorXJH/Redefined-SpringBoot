package com.example.springboot2thymeleaf.exception;

import lombok.SneakyThrows;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 自定义异常解析器
 * @date 2022/9/29 12:48
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)//优先级，数字越小优先级越高
@Component
public class CustomerHandlerExceptionResolver implements HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.sendError(511,"我喜欢的错误");
        return new ModelAndView();
    }
}