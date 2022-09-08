package com.example.springbootsecurity;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StringEncryptor stringEncryptor;
    @Test
    public void jasyptTest() {
        // 加密
        System.out.println(stringEncryptor.encrypt("ColorXJH"));
    }

    @Test
    void contextLoads() {
    }

}
