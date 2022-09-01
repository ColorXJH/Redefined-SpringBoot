package com.example.springbootjmsactivemqprovider.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/1 15:38
 */
@Configuration
public class ProviderConfig {

    //定义存放消息的队列
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("MyActiveMQQueue");
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("MyActiveMQTopic");
    }
}
