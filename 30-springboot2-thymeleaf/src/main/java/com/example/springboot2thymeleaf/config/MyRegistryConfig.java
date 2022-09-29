package com.example.springboot2thymeleaf.config;

import com.example.springboot2thymeleaf.servlet.MyFilter;
import com.example.springboot2thymeleaf.servlet.MyListener;
import com.example.springboot2thymeleaf.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 另一种注册servlet ,filter,listener的方式
 * @date 2022/9/29 16:02
 */
@Configuration(proxyBeanMethods = true)//保证依赖的组件始终是单实例的
public class MyRegistryConfig {

    //为什么这个路径没有被拦截器拦截呢
    //1:myServlet-->/my,/my02 Tomcat-Servlet:多个servlet能同时处理到同一层路径，优先匹配原则，/与/my 访问/my路径会优先匹配到MyServlet
        //所以不经过spring的DispatcherServlet处理
    //2:DispatcherServlet-->/  通过ServletRegistrationBean把DispatcherServlet注入进来
    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet=new MyServlet();
        return new ServletRegistrationBean(myServlet,"/my","/my02");
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        MyFilter myFilter=new MyFilter();
        //拦截myservlet规定的额路径
        //return new FilterRegistrationBean(myFilter,myServlet());
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myFilter"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MyListener listener=new MyListener();
        ServletListenerRegistrationBean listenerRegistrationBean=new ServletListenerRegistrationBean(listener);
        return listenerRegistrationBean;

    }
}
