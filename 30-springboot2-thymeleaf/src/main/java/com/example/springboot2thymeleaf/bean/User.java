package com.example.springboot2thymeleaf.bean;

import lombok.Data;

import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/27 14:13
 */
@Data
public class User {
    String userName;
    String password;
    List<Person> records;
    Integer pages;
    Integer total;
    Integer current;
}