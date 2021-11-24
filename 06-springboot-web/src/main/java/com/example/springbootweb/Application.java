package com.example.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//web开发
    //1：创建springboot应用，选中我们需要的模块
    //2：springboot已经默认将这些场景都配置好了，只需要在配置文件中指定少量配置就可以运行起来
    //3：自己编写代码
        //自动配置原理;这个场景springboot帮我们配置了什么，能不能修改？，能修改哪些配置，能不能扩展？
        //xxxAutoConfiguration类：帮我们给容器中自动配置组件
        //xxxProperties类：配置类来封装配置文件的类容
