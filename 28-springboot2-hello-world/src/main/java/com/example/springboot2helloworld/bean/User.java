package com.example.springboot2helloworld.bean;

import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/19 15:23
 */
@Data
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
