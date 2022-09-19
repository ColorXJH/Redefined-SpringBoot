package com.example.springboot2helloworld.bean;

import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/19 16:44
 */
@Data
public class Pig {
    private String name;
    private Integer age;

    public Pig() {
        this("HAHA",2);
    }

    public Pig(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
