package com.example.springbootrabbitmq;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    //管理中间件队列，交换器
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Test
    public void test221(){
        //创建exchange,Queue,Binding
        amqpAdmin.declareExchange(new DirectExchange("ColorExchange"));
        amqpAdmin.declareQueue(new Queue("ColorQueue",true));
        amqpAdmin.declareBinding(new Binding("ColorQueue", Binding.DestinationType.QUEUE,"ColorExchange","color.*",null));
    }

    @Test
    public void jasyptTest() {
        // 加密
        System.out.println(stringEncryptor.encrypt("guest"));
        System.out.println(stringEncryptor.decrypt("rLOMfhvqU1r+6j9NbzVLRZRi8jcQaATKbag3efuew/0eLJqrs7+NU9H18ITMolP6"));
    }


    @Test
    void contextLoads() {
        Map<String,Object>map=new HashMap<>();
        map.put("name","Color6");
        map.put("age",28);
        rabbitTemplate.convertAndSend("model.exchange.topic.name","user.names.color6",map);
    }

    @Test
    void test2() {
        System.out.println(rabbitTemplate.receiveAndConvert("model.queue.name.topic123"));
    }



}
