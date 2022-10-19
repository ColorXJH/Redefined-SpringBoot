package com.example.springboot2usestarter.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/19 17:22
 */
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("--runner--");
    }
}
