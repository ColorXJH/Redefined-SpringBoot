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