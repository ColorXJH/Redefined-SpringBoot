package com.example.springbootmystarter.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/16 16:50
 */
@Data
@ConfigurationProperties("hello")
public class HelloProperties {

    private String name;
    private String addr;
    private Integer age;
}
