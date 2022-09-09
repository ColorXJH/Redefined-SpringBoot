package com.example.springbootsecurity;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
        System.out.println(encoder.encode("234"));
        System.out.println(encoder.encode("345"));
        System.out.println(encoder.encode("456"));
    }

}
