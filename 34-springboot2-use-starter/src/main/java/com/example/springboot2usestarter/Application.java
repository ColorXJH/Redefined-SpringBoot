package com.example.springboot2usestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    //SpringApplication启动器的创建与运行过程

    //@PropertySource配置的用法
    //加载指定的属性文件（*.properties）到 Spring 的 Environment 中。可以配合 @Value 和
    //@ConfigurationProperties 使用。
    //@PropertySource 和 @Value
    //组合使用，可以将自定义属性文件中的属性变量值注入到当前类的使用@Value注解的成员变量中。
    //@PropertySource 和 @ConfigurationProperties
    //组合使用，可以将属性文件与一个Java类绑定，将属性文件中的变量值注入到该Java类的成员变量中。

}
