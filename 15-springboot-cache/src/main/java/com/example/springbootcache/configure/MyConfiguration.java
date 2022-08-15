package com.example.springbootcache.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/8/15 15:40
 */
@Configuration
public class MyConfiguration {

    //3:定义一个过滤器
    //@Bean
    public CorsFilter corsFilter(){
        System.out.println("---colorxjh---");
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        //设置http头信息
        corsConfiguration.addAllowedOrigin("*");//允许什么域名来请求
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);
    }
}
