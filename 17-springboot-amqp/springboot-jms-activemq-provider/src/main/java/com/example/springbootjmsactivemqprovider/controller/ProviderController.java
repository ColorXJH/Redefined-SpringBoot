package com.example.springbootjmsactivemqprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/1 15:41
 */
@RestController
public class ProviderController {
    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @GetMapping("/provider/queueSend/{msg}")
    public String sendQueue(@PathVariable("msg") String msg){
        jmsMessagingTemplate.convertAndSend(queue, msg);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", msg);
        return msg;
    }


    @GetMapping("/provider/topicSend/{msg}")
    public String sendTopic(@PathVariable("msg") String msg){
        jmsMessagingTemplate.convertAndSend(topic, msg);
        return msg;
    }



    //查看 ActiveMQ 管理页面
    //Number Of Pending Messages：消息队列中待处理的消息
    //Number Of Consumers：消费者的数量
    //Messages Enqueued：累计进入过消息队列的总量
    //Messages Dequeued：累计消费过的消息总量
}
