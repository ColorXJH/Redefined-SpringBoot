package com.example.springbootstartertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
//如果不能提交，检查git和本地的CRLF 还是LF 保持一致
//或者关闭代码提示分析，有时候一直分析不出来影响commit提交

//https://zhuanlan.zhihu.com/p/453338913      6 个 Github 项目拿下 Spring Boot
//github官方代码继承小demo再分支1.5x下可以找到：https://github.com/spring-projects/spring-boot main下面找分支branch 1.5x
//我将她放在了本羡慕的static目录下
