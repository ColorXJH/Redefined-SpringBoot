package com.example.autoconfigure;

import com.example.HelloProperties;
import com.example.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 16:28
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)//绑定属性，默认HelloProperties组件放到容器中
public class HelloServiceAutoConfigure {
    @ConditionalOnMissingBean(HelloService.class)
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}

//如何使用，需要使用场景启动器ColorXJH-Module，则其引入的自动配置包ColorXJH-AutoConfigure
//需要先打包好，（clean+ install先将ColorXJH-AutoConfigure安装到本地仓库，），
//然后将我们的场景启动器starter(ColorXJH-Module)放入到本地仓库(clean+install)