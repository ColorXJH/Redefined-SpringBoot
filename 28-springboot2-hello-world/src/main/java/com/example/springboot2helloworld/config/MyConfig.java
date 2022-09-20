package com.example.springboot2helloworld.config;

import com.example.springboot2helloworld.bean.MyCar;
import com.example.springboot2helloworld.bean.Pet;
import com.example.springboot2helloworld.bean.Pig;
import com.example.springboot2helloworld.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/19 15:30
 */
@Import(Pig.class)
@ImportResource(locations = {"classpath:beans.xml"})//加载外部资源
@Configuration
@EnableConfigurationProperties(MyCar.class)
public class MyConfig {

    @Bean //方法名作为组件id
    public User user1(){
        return new User("张三",18);
    }
    @Bean //方法名作为组件id
    public Pet tomcatPet(){
        return new Pet("tomcat-pet");
    }
}
