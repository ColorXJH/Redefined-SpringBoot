package com.example.springboot2helloworld.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/20 9:59
 */
@Data
@ConfigurationProperties(prefix = "mycar")
public class MyCar {
    private String name;
    private Integer price;
}
