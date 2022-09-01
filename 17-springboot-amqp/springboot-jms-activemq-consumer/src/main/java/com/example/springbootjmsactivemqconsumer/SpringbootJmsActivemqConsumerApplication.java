package com.example.springbootjmsactivemqconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;
//如果SpringBoot项目不需要用到数据库，那么就不用配置数据库。但是不配置数据库会报错，所以需要禁用SpringBoot数据库库的自动配置
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@EnableJms//启动消息队列
public class SpringbootJmsActivemqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJmsActivemqConsumerApplication.class, args);
    }

}
