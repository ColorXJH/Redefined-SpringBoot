package com.example.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/13 14:17
 */
@RestController
public class TestController {
    @Value("${color.name}")
    private String name;
    @GetMapping("/testName")
    public String testName(){
        return name;
    }
}
