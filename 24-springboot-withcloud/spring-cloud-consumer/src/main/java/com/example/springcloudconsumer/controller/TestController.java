package com.example.springcloudconsumer.controller;

import com.example.springcloudconsumer.feign.FeignService;
import com.example.springcloudconsumer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/15 17:02
 */
@RestController
public class TestController {
    @Autowired
    TestService service;
    @Autowired
    FeignService feignService;
    @GetMapping("/test/{name}")
    public String sayHelloToWho(@PathVariable("name")String name){
        return service.sayHelloToWho(name);
    }

    @GetMapping("/testFeign/{name}")
    public String feignTest(@PathVariable("name")String name){
        return feignService.testSayHello(name);
    }
}
