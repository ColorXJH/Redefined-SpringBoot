package com.example.springboot2helloworld.controller;

import com.example.springboot2helloworld.bean.MyBeans;
import com.example.springboot2helloworld.bean.MyCar;
import com.example.springboot2helloworld.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/19 11:28
 */
@RestController
public class HelloController {
    @GetMapping("/test")
    public String sayHello(){
        System.out.println("--beans--"+beans);
        System.out.println("--car--"+car);
        return "Hello-World";
    }

    MyBeans beans;
    @Autowired
    public void setBeans(MyBeans beans){
        this.beans=beans;
    }
    @Autowired
    MyCar car;

    @Autowired
    Person person;
    @GetMapping("/person")
    public Object getPerson(){
        return person;
    }
}
