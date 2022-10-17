package com.example.springboot2profile.config;

import com.example.springboot2profile.entity.P1Inf;
import com.example.springboot2profile.entity.P2Inf;
import com.example.springboot2profile.inf.PersonInf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 14:38
 */
public class MyConfig {
    @Profile("prod")
    @Bean
    public PersonInf p1(){
        return new P1Inf();
    }
    @Profile("test")
    @Bean
    public PersonInf p2(){
        return new P2Inf();
    }
}
