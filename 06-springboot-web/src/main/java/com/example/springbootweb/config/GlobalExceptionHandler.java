package com.example.springbootweb.config;

import com.example.springbootweb.exception.LoginOutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName: GlobalExceptionHandler
 * @Package: com.example.springbootweb.config
 * @Description: 该注解描述的类为控制全局异常处理类，在此类中定义一场处理方法
 * @Datetime: 2022/5/19 17:32
 * @author: ColorXJH
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginOutException.class)
    public String doLoginOutException(LoginOutException e){
        e.printStackTrace();
        return "redirect:/index.html";
    }
}
