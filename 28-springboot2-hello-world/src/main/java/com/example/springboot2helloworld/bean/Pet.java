package com.example.springboot2helloworld.bean;

import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/19 15:24
 */
@Data
public class Pet{
    private String  name;

    public Pet(String name) {
        this.name = name;
    }
}
