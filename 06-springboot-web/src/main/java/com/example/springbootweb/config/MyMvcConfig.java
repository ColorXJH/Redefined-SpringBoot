package com.example.springbootweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyMvcConfig
 * @Package: com.example.springbootweb.config
 * @Description:
 * @Datetime: 2022/2/21 20:32
 * @author: ColorXJH
 */
//idea中查找类：右上方放大镜搜索按钮，或者双击shift按钮弹出
//使用WebMvcConfigurerAdapter可以扩展springmvc得功能，基于接口WebMvcConfigurer实现得空实现，自定义配置
@EnableWebMvc //全面接管后，所有的springmvc的自动配置都失效，一些静态资源的页面也就无法访问（只是体现之一  ）
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    //ctrl+o  查看重写方法快捷键
    //alt+insert 重写方法得快捷键
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println(hello+"------------------------"+Arrays.toString(users));
        //super.addViewControllers(registry);
        //浏览器发送“/atHello”请求，也来到success页面,
        registry.addViewController("/atHello").setViewName("success");
    }
    @Value("${hello}")
    private String hello;
    @Value("${users}")
    private String[] users;
}
