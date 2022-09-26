package com.example.springboot2web.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/26 11:33
 */
@Data
public class Person {
    Pet pet;
    String userName;
    Integer age;
    Date birth;
}
