package com.example.springboot2helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/19 11:28
 */
@RestController
public class HelloController {
    @GetMapping("/test")
    public String sayHello(){
        return "Hello-World";
    }
}
