package com.example.springcloudprovider.controller;

import com.example.springcloudprovider.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/15 16:58
 */
@RestController
public class HelloController {
    @Value("${server.port}")
    String port;

    @Autowired
    HelloService service;
    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name){
        System.out.println("this port is "+port);
        return service.sayHello(name);
    }
}
