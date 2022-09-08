package com.example.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    //注意使用springSecurity项目就被保护起来了，初次启动，会有密码在控制台打印，默认账号user
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
