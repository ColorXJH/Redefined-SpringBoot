package com.example.springbootconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Package: com.example.springbootconfig.controller
 * @Description:
 * @Datetime: 2021/11/20 16:46
 * @author: ColorXJH
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }
}
