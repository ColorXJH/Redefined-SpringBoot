package com.example.springboot2usestarter.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/19 17:17
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("--init--");
    }
}
