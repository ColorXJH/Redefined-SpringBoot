package com.example.springboot2usestarter.listener;

import org.springframework.boot.CommandLineRunner;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/19 17:23
 */
public class MyCommindLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("--line-runner---");
    }
}
