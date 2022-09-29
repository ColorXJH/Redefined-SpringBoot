package com.example.springboot2thymeleaf.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 处理整个web controller的异常
 * @date 2022/9/29 12:03
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})//处理异常
    public String handlerArithException(Exception e){
        log.error("异常是{}",e.toString());
        return "login";     //也可以返回ModelAndView
    }
}
