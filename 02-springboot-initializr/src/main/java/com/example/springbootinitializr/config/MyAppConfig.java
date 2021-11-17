package com.example.springbootinitializr.config;

import com.example.springbootinitializr.services.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MyAppConfig
 * @Package: com.example.springbootinitializr.config
 * @Description: 指明当前类是一个配置文件，就是用来替代spring的配置文件
 * @Datetime: 2021/11/17 22:38
 * @author: ColorXJH
 */
@Configuration
public class MyAppConfig {
    @Bean //将方法的返回值添加到容器中，容器中这个组件默认的id就是方法名
    public HelloService helloService(){
        System.out.println("给容器中添加组件----");
        return new HelloService();
    }
}
