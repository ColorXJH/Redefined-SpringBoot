package com.example.springboot2thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/27 17:17
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Bean
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        return restTemplate;
    }

    /*Filter与Interceptor几乎拥有相同的功能，到底用哪个好？
    *   filter 是servlet定义的原生组件，好处是脱离spring也能应用
    *   interceptor是spring定义的接口，只能在spring框架中使用，当然也就可以使用spring的autowired自定装配功能了
    */

    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Autowired
    URLInterceptor urlInterceptor;
    //访问/static/** 下的所有请求，都去classpath:/static/下面进行匹配相应的资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor )
                .addPathPatterns("/**")
                //这种方法配合配置文件spring.mvc.static-path-pattern=/static/** 或者上方addResourceHandlers写法，可以设置静态资源访问的路径从/static开始
                //就是说以后访问静态资源都必须要带前缀/static，前端页面的href等静态资源加载路径都要添加前缀
                .excludePathPatterns("/","/login","/static/**");

                //2:但是如果我们使用这种方法，就不需要改变前缀，表示要放行哪些静态资源
                //.excludePathPatterns("/","/login","/css/**","/js/**","/fonts/**","/images/**");


                //这两种情况究其原因：static是执行完controller之后在view视图接口的render里面拼接的，
                //拦截器执行时机在controller之前，所以不行
        registry.addInterceptor(redisUrlCountInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/static/**");
    }
}


/*
 关于springboot2静态资源被拦截的问题
         springBoot版本问题
         SpringBoot2.X不再自动放行静态资源，
         添加拦截器后需要在mvc配置中exclude静态资源路径，
         即在excludePathPatterns()方法中添加"/static/**"参数。

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/index.html","/","/user/login","/static/**");
        }


        在application.properties文件中添加
// spring.mvc.static-path-pattern  配置映射路径规则 默认值是/**
// spring.resources.static-locations 配置静态资源位置
        spring.mvc.static-path-pattern=/static*/
/**
 // 如果不配置的话，默认是spring.mvc.static-path-pattern=/**
 更改页面中的href及src
 springboot添加拦截器之后，不认springboot之前默认访问的static文件夹，需在原有访问路径前添加/static/
 添加拦截器前 <link rel="stylesheet" th:href="@{plugins/layui/css/layui.css}">
 添加拦截器后 <link rel="stylesheet" th:href="@{/static/plugins/layui/css/layui.css}">*/
