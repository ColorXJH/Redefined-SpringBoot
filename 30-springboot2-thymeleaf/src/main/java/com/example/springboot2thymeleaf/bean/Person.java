package com.example.springboot2thymeleaf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 11:37
 */
@Data
@AllArgsConstructor
public class Person {
    public String id;
    public String name;
    public Integer age;
    public String email;
}
