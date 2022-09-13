package com.example.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/13 9:36
 */
@RestController
@RequestMapping("/HelloController")
public class HelloController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/hello")
    public String sayHello(){
        return "hello,this is provider";
    }
    @GetMapping("/serviceUrl")
    public String serviceUrl(){
        List<ServiceInstance> list=discoveryClient.getInstances("zookeeper-provider");
        if(list!=null&&list.size()>0){
            return list.get(0).getUri().toString();
        }
        return null;
    }
}
