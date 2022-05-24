package com.example.springbootweb.exception;

/**
 * @ClassName: MyException
 * @Package: com.example.springbootweb.exception
 * @Description:
 * @Datetime: 2022/5/24 17:14
 * @author: ColorXJH
 */
public class MyException extends RuntimeException{
    public MyException(){
        super("用户名无效");
    }
}
