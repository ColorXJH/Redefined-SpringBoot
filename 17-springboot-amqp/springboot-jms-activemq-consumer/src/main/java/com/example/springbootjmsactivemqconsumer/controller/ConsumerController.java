package com.example.springbootjmsactivemqconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/1 15:58
 */
@RestController
public class ConsumerController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 使用JmsListener配置消费者监听的队列
    @JmsListener(destination = "MyActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("Color6")
    public void receiveQueueMessage(String msg){
        System.out.println("1成功接受消息：--:"+msg);
    }

    // 使用JmsListener配置消费者监听的队列
    @JmsListener(destination = "MyActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("Color6")
    public void receiveQueueMessage2(String msg){
        System.out.println("2成功接受消息：--:"+msg);
    }

    @JmsListener(destination = "MyActiveMQTopic")
    public void receiveTopicMessage(String msg){
        System.out.println("收到msg---:"+msg);
    }
    @JmsListener(destination = "MyActiveMQTopic")
    public void receiveTopicMessage2(String msg){
        System.out.println("2收到msg---:"+msg);
    }

}
