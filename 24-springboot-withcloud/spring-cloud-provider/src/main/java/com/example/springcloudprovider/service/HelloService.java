package com.example.springcloudprovider.service;

import org.springframework.stereotype.Service;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/15 16:57
 */
@Service
public class HelloService {

    public String sayHello(String name){
        return "hello "+name;
    }
}
