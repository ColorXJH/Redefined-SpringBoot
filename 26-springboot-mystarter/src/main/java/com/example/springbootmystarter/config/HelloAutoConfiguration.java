package com.example.springbootmystarter.config;

import com.example.springbootmystarter.bean.HelloProperties;
import com.example.springbootmystarter.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/16 16:57
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService(){
        return new HelloService();
    }
}
