package com.example.springboot2helloworld.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/20 9:53
 */
@Component
@ConfigurationProperties(prefix = "color")
@Data
public class MyBeans {
    private String name;
    private String age;

}
