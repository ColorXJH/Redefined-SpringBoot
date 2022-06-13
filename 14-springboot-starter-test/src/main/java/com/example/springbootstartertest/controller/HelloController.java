package com.example.springbootstartertest.controller;

import com.example.springbootstarterautoconfigurer.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/13 18:15
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello(){
        return helloService.sayHello("haha");
    }
}
