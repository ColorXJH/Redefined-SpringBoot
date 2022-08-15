package com.example.springbootcache.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/8/15 14:57
 */

//2:重写WebMvcConfigurer接口的addCorsMappings方法
//@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    //添加映射路径
    // /** 代表所有路径都允许跨域
    // /emp/* 代表/emp开头的的路径跨域
    //  /emp/1 代表指定路径跨域
    //  /emp/1* 代表/emp/1和/emp/1开头的路径跨域
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/emp/*")
                .allowedOriginPatterns("*")//设置放行那些原始域，来自哪里的跨域可以放行
                .allowedHeaders("*")//放行哪些原始请求头部信息
                .exposedHeaders("*");//暴露哪些原始请求头信息
    }
}
