package com.example.springboot2thymeleaf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/29 12:29
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多")
public class UserToManyException extends RuntimeException{
    public UserToManyException(){}
    public UserToManyException(String msg){
            super(msg);
    }
}
