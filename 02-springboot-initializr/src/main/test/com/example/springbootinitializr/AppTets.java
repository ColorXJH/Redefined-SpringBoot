package com.example.springbootinitializr;

import com.example.springbootinitializr.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2021/11/15 11:55
 */
@SpringBootTest
public class AppTets {
    @Autowired
    private Person person;

    @Test
    public void test(){
        System.out.println(person);
    }
}
