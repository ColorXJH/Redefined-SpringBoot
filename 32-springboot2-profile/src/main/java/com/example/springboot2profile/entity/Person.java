package com.example.springboot2profile.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 14:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    String name;
    String addr;
    Integer age;
}
