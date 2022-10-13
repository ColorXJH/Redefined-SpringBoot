package com.example.springboot2thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
//指定原生的servlet组件放在哪里
@ServletComponentScan(basePackages = "com.example.springboot2thymeleaf")
@MapperScan("com.example.springboot2thymeleaf.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
