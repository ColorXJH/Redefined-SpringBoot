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
        System.out.println(stringEncryptor.decrypt("rLOMfhvqU1r+6j9NbzVLRZRi8jcQaATKbag3efuew/0eLJqrs7+NU9H18ITMolP6"));
    }


    @Test
    void contextLoads() {
    }

}
