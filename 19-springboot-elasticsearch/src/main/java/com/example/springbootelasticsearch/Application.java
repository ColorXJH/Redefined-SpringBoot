package com.example.springbootelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* springboot支持两种技术和es交互
* 1：客户端（分为高级和更新后的统一客户端，8.0之后推荐使用Elasticsearch Java API 客户端 ）
* 2：spring-data-elasticsearch(spring封装后的，包含restTemplate和repository两种)
* */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
