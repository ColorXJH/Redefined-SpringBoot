package com.example.service;

import com.example.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 16:20
 */
//默认不要放在容器中，需要我们配置
public class HelloService {
    @Autowired
    HelloProperties helloProperties;

    public String sayHello(String userName){
        System.out.println(userName);
        return helloProperties.getPrefix()+":"+userName+":"+helloProperties.getSuffix();
    }
}
