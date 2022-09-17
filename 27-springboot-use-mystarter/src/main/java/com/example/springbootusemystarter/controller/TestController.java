package com.example.springbootusemystarter.controller;

import com.example.springbootmystarter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Package: com.example.springbootusemystarter.controller
 * @Description:
 * @Datetime: 2022/9/17 18:53
 * @author: ColorXJH
 */
@RestController
public class TestController {
    @Autowired
    HelloService helloService;

    @GetMapping("/testMyStarter")
    public String testMyStarter(){
        return helloService.sayHello();
    }
}
