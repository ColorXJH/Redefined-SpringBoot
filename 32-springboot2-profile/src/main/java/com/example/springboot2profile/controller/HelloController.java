package com.example.springboot2profile.controller;

import com.example.springboot2profile.entity.Person;
import com.example.springboot2profile.inf.PersonInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 13:53
 */
@RestController
public class HelloController {
    @Value("${name:Color}")
    private String name;
    @GetMapping("/hello")
    public String sayHello(){
        System.out.println(person);
        return "hello: "+ name;
    }
    @Autowired
    private Person person;
    @Autowired
    private PersonInf pss;

    @GetMapping("/type")
    public String getType(){
        System.out.println(pss);
        return pss.getClass().toString();
    }
}
