package com.example.springboot2usestarter.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/19 17:18
 */
public class MyApplicationListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("----");
    }
}
