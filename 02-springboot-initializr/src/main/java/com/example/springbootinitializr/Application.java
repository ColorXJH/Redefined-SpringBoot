package com.example.springbootinitializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    //使用idea的initializr生成，帮助我们生成了resources文件夹结构 static放静态资源 templates 保存所有模板页面（springboot默认jar包使用嵌入式tomcat,默认不支持jsp页面，可以使用模板引擎）
        //freemarker thymeleaf
    //application.properties:springboot应用的配置文件,或者使用application.yml,作用是修改springboot自动配置的默认值
    //配置文件放在src/main/resources目录或者 类路径/config下
        //xml <server><port>8080</port></server>
        //yml  key:(空格)value
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
