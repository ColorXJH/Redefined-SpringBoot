package com.example.springboot2helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Value("${server.port}")
    String port;
    @Test
    void contextLoads() {
        System.out.println("--test start--");
        System.out.println(port);
    }

}
