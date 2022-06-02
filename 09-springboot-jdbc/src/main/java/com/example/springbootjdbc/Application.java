package com.example.springbootjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//springboot 与数据访问
    //jdbc              mybatis         springboot data jpa
//简介
    //对于数据访问层，无论是SQL还是NOSQL,springboot默认采用整合spring data的方式进行统一处理
    //添加了大量的自动配置，屏蔽了很多设置，引入各种xxxTemplate，xxxRepository来简化我们对数据访问层的操作
    //对我们来说只需要进行简单的设置即可，考虑使用SQL，NOSQL在缓存，消息，检索等方面测试

//整合基本的JDBC与数据源
    //1：引入starter=>spring-boot-starter-jdbc
    //2：配置application.yml
    //3：测试
    //4：高级配置：使用druid数据源
        //引入druid,配置属性
    //5：配置druid数据源监控
        //一下为如何安装docker以及在docker钟安装mysql
            //https://blog.csdn.net/cbh1987/article/details/120481157
            //https://blog.csdn.net/xtkinglong/article/details/123341676
