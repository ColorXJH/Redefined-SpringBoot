package com.example.springbootlogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
//jdbc--数据库驱动
    //写一个同一的接口层，日志门面（日志的一个抽象层）
    //给项目中导入具体的日志实现就行了，具体日志框架实现抽象层
//市面上的日志框架
    //JUL JCL Jboss-logging logback log4j log4j2 slf4j
    //日志门面：JCL  SLF4J JBoss-logging    //日志实现 log4j  JUL  log4j2  logback    门面+实现==》SLF4J+logback
//springboot:底层是spring框架，spring框架默认使用JCL(jakarta commons logging),springboot选用slf4j+logback