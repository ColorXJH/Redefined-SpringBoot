package com.example.springboot2web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/21 10:04
 */
@Controller
public class ReIndexController {
    /*以下两个方法都可以重写，一个是请求转发，另一个是跳转到对应模板*/
    /*@GetMapping("/")
    public String newIndex(){
        return "forward:hello.html";
    }*/
    @GetMapping("/")
    public String getIndex(){
        return "login";//默认static目录下的login.html
    }
}
