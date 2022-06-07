package com.example.springbootdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
//springboot整合jpa
//1:引入spring-boot-starter-data-jpa
//2:配置文件打印sql语句
//3:创建Entity标注jpa注解
//4:创建Repository接口继承JpaRepository
//5:测试方法

//spring data
//spring data项目的目的是为了简化构建基于spring框架应用的数据访问技术，包括非关系数据库，
//map-reduce框架，云数据服务等等，另外也包含对关系型数据库的支持
//spring data包含多个子项目
//spring data commons
//spring data jpa
//spring data ldap
//spring data redis
//spring data rest
//...
//spring data为我们提供使用统一的api来对数据访问层进行操作，这个主要由spring data commons项目来实现
//spring data commons让我们在使用关系型/非关系型数据访问技术时都基于spring提供的统一标准，标准包含了curd,查询，排序和分页的相关操作

//面向 spring data 编程（spring data提供了 repository,template,objectMapping，供我们使用）
//spring data 默认使用hibernate 对于jpa规范的实现
//jsr303--hibernate validate
//jsr317--jpa--hibernate