package com.example.springbootstartupstep.listener;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/13 9:08
 */
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    //注意此构造器必须写，不然报错
    public HelloSpringApplicationRunListener(SpringApplication sa, String[] args) {

    }
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("SpringApplicationRunListener....starting");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        Object o = environment.getSystemProperties().get("os.name");
        System.out.println("environmentPrepared..... "+o);

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
            System.out.println("contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("contextLoaded....");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("started........");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.started(context);
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("ready........");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.running(context);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("failed.....");
    }

}
