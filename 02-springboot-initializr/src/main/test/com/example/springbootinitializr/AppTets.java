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
    //单元测试的测试类一定要和启动类在同一个根目录下。
    @Test
    public void test(){
        System.out.println(person);
    }
}
