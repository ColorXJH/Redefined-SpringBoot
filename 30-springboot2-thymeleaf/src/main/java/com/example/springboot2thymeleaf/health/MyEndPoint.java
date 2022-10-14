package com.example.springboot2thymeleaf.health;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 自定义endpoint
 * @date 2022/10/14 15:37
 */
@Component
@Endpoint(id="color6")
public class MyEndPoint {
    @WriteOperation
    public void writeSomething(){
        System.out.println("--do something--");
    }
    @ReadOperation
    public Map getSomethingInfo(){
        return Collections.singletonMap("info","this is color life");
    }
}
