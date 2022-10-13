package com.example.springboot2thymeleaf.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/13 14:06
 */
@Data
public class MyPerson {
    String userName;
    Integer age;
    Date birth;
}
