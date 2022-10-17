package com.example.springboot2profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println(systemProperties);
        System.out.println(environment);
    }

}
