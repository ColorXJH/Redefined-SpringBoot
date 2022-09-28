package com.example.springboot2thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/27 17:17
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Autowired
    URLInterceptor urlInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(urlInterceptor )
                .addPathPatterns("/**")
                //不生效可以在static目录assert下定一个新的文件夹然后/assert/**.同样可以达到配置文件
                //spring.mvc.static-path-pattern=/static/** + .excludePathPatterns("/","/login","/static/**");
                //的目的
                .excludePathPatterns("/","/login","/static/**");
    }
}
