package com.example.springbootsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value="com.example.springbootsecurity.dao")
@SpringBootApplication
public class Application {
    //注意使用springSecurity项目就被保护起来了，初次启动，会有密码在控制台打印，默认账号user
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//安全
//spring security是针对spring项目的安全框架，也是springboot底层安全模块默认的技术选型，它可以实现强大的web安全控制，对于安全控制，我们仅需要引入
//spring-boot-starter-security模块，进行少量的配置，即可实现强大的安全管理

//几个类：
    //1:WebSecurityConfigurerAdapter:自定义security策略
    //2:AuthenticationManagerBuilder:自定义认证策略
    //3:@EnableWebSecurity:开启web security模式

//有用程序的两个主要区域是：“认证”和“授权”（或者访问控制），这两个主要区域是spring security的两个目标

//认证：（Authentication）是建立一个他声明的主体的过程（一个主体一般是指用户，设备或一些可以在你的应用中执行动作的其他系统）
//授权：（Authorization）指确定一个主体是否允许在你的应用程序执行一个动作的过程，为了需要抵达授权的店，主体的身份已经有认证过程建立
//这个概念是通用的而不是spring security独有的