package com.example.springcloudconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/15 17:01
 */
@Service
public class TestService {

    @Autowired
    RestTemplate restTemplate;

    public String sayHelloToWho(String name){
        return restTemplate.getForObject("http://PROVIDER/hello/"+name,String.class);
    }
}
