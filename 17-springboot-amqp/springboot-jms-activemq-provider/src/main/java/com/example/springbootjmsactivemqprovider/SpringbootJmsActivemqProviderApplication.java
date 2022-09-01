package com.example.springbootjmsactivemqprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms//启动消息队列
//@EnableJms 会启动 jms 的注解扫描即发现 @JmsListener 注释的方法创建消息监听容器，相当于 <jms:annotation-d riven/>
public class SpringbootJmsActivemqProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJmsActivemqProviderApplication.class, args);
    }

}

        /* 注意事项
        队列模式(Queue):
            如果生产者产生了100条消息，那么两个消费者同时在的话，会分工合作来接收这100条消息，就是每个消费者接收到50条来处理，轮询接收。
        主题模式(topic):
            如果生产者产生了100条消息，消费者在还没有订阅这个主题之前，是不会接收到这100条消息的。
            消费者只有在订阅了某个主题消息之后，生产者产生的消息 才会被接收处理。
            如果又两个消费者同时订阅了这个主题消息，生产者在产生100条消息时，两个消费者会同时分别接收到这100条消息。
        */
