package com.example.springbootrabbitmq.provider;

import com.example.springbootrabbitmq.config.RabbitCommonConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/2 12:45
 */
@RestController
public class RabbitProvider {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    /**
     * Description: direct 交换器测试 以routingKey绑定队列
     * @Author: ColorXJH
     * @Date: 2022/9/2 12:56
     * @param msg
     * @Return: java.lang.String
     **/
    @GetMapping("/doDirect/{msg}")
    public String doDirect(@PathVariable("msg")String msg){
        //默认交换器发送
        rabbitTemplate.convertAndSend(RabbitCommonConstants.MODEL_QUEUE_NAME_DIRECT1,msg);
        /*指定交换器发送*/
        rabbitTemplate.convertAndSend(RabbitCommonConstants.MODEL_EXCHANGE_DIRECT_NAME,RabbitCommonConstants.MODEL_QUEUE_NAME_DIRECT2,msg);
        return "==doDirect==:"+msg;
    }
    
    /**
     * Description: fanout 交换器测试 发送给绑定该交换器的全部队列
     * @Author: ColorXJH
     * @Date: 2022/9/2 12:59
     * @param msg
     * @Return: java.lang.String
     **/
    @GetMapping("/doFanout/{msg}")
    public String doFanout(@PathVariable("msg")String msg){
        rabbitTemplate.convertAndSend(RabbitCommonConstants.MODEL_EXCHANGE_FANOUT_NAME,RabbitCommonConstants.COMMON_EMPTY_STRING,msg);
        return "==doFanout==:"+msg;
    }

    /**
     * Description: topic 交换器测试 发送给匹配的队列
     * @Author: ColorXJH
     * @Date: 2022/9/2 12:59
     * @param msg
     * @Return: java.lang.String
     **/
    @GetMapping("/doTopic/{msg}")
    public String doTopic(@PathVariable("msg")String msg){
        //交换器+路由键+数据==》监听者需要定义交换器，队列名称，以及路由键规则
        rabbitTemplate.convertAndSend(RabbitCommonConstants.MODEL_EXCHANGE_TOPIC_NAME,RabbitCommonConstants.MODEL_QUEUE_INFO_TOPIC,msg+"info");
        rabbitTemplate.convertAndSend(RabbitCommonConstants.MODEL_EXCHANGE_TOPIC_NAME,RabbitCommonConstants.MODEL_QUEUE_ERROR_TOPIC,msg+"error");
        return "==doTopic==:"+msg;
    }
}
