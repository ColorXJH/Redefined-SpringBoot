package com.example.springbootmystarter.service;

import com.example.springbootmystarter.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/16 16:54
 */
public class HelloService {
    @Autowired
    private HelloProperties helloProperties;


    public String sayHello(){
        return helloProperties.getAddr()+helloProperties.getName()+helloProperties.getAge();
    }
}
