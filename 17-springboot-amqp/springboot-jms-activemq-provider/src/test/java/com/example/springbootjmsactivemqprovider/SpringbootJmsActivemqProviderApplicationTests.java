package com.example.springbootjmsactivemqprovider;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootJmsActivemqProviderApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private StringEncryptor stringEncryptor;
    @Test
    public void jasyptTest() {
        // 加密
        System.out.println(stringEncryptor.encrypt("admin"));
        System.out.println(stringEncryptor.encrypt("2012WananXJH"));
    }
}
