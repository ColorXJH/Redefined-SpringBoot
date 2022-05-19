package com.example.springbootweb.exception;

/**
 * @ClassName: LoginOutException
 * @Package: com.example.springbootweb.exception
 * @Description:
 * @Datetime: 2022/5/19 17:18
 * @author: ColorXJH
 */
public class LoginOutException extends Exception{
    public LoginOutException(){
        super();
    }

    public LoginOutException(String message){
        super(message);
    }
}
