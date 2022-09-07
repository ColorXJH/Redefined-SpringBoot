package com.example.springboottask.controller;

import com.example.springboottask.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 13:53
 */
@RestController
public class AsyncController {
    @Autowired
    private AsyncService service;
    @GetMapping("/hello")
    public String sayHello(){
        service.hello();
        return "success";
    }
    @GetMapping("/async/hello")
    public String asyncSayHello(){
        service.asyncHello();
        return "success";
    }
}
