package com.example.springbootrabbitmq;

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
        System.out.println(stringEncryptor.encrypt("guest"));
        // 解密
        System.out.println(stringEncryptor.decrypt("Uu2equIvUxBezquRPJ7P3yOFrAoConIQ4q+mWcSWsOoVOSeWRrxtg2mwwOfJ5x9S"));

    }


    @Test
    void contextLoads() {
    }

}
