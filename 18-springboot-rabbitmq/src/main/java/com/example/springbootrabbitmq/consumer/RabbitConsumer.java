package com.example.springbootrabbitmq.consumer;

import com.example.springbootrabbitmq.config.RabbitCommonConstants;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/2 12:45
 */
@Component
public class RabbitConsumer {

    /**
     * Description: direct消费者  默认交换器接收
     * @Author: ColorXJH
     * @Date: 2022/9/2 13:13
     * @param msg
     * @Return: void
     **/
    //注解指定目标方法来作为消费消息的方法，通过注解参数指定所监听的队列或者Binding，可以配置concurrency设置线程数，主要配置mq链接、声明方面的配置参数
    //@RabbitListener(queuesToDeclare ={@Queue(RabbitCommonConstants.MODEL_QUEUE_NAME_DIRECT1)},concurrency ="1-2" )
    public void doDirectConsumer1(String msg){
        System.out.println("--doDirectConsumer1--:"+msg);
    }
    /**
     * Description: direct消费者 指定交换器接收
     * @Author: ColorXJH
     * @Date: 2022/9/2 13:15
     * @param msg
     * @Return: void
     **/
    /*@RabbitListener(
            bindings = {@QueueBinding(
                exchange = @Exchange(value = RabbitCommonConstants.MODEL_EXCHANGE_DIRECT_NAME,type = ExchangeTypes.DIRECT),
                    value = @Queue(RabbitCommonConstants.MODEL_QUEUE_NAME_DIRECT2),
                    key = RabbitCommonConstants.MODEL_QUEUE_NAME_DIRECT2
            )},concurrency = "1-2"
    )*/
    public void doDirectConsumer2(String msg){
        System.out.println("--doDirectConsumer2--:"+msg);
    }


    /**
     * Description: fanout消费者1号
     * @Author: ColorXJH
     * @Date: 2022/9/2 13:21
     * @param msg
     * @Return: void
     **/
    /*@RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = RabbitCommonConstants.MODEL_EXCHANGE_FANOUT_NAME,type = ExchangeTypes.FANOUT),
            value =@Queue(value = RabbitCommonConstants.MODEL_QUEUE_NAME_FANOUT1)
    ),concurrency = "1-2")*/
    public void doFanoutConsumer1(String msg){
        System.out.println("--doFanoutConsumer1--:"+msg);
    }
    /**
     * Description: fanout消费者2号
     * @Author: ColorXJH
     * @Date: 2022/9/2 13:21
     * @param msg
     * @Return: void
     **/
    /*@RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = RabbitCommonConstants.MODEL_EXCHANGE_FANOUT_NAME,type = ExchangeTypes.FANOUT),
            value =@Queue(value = RabbitCommonConstants.MODEL_QUEUE_NAME_FANOUT2)
    ),concurrency = "1-2")*/
    public void doFanoutConsumer2(String msg){
        System.out.println("--doFanoutConsumer2--:"+msg);
    }
    /**
     * Description: fanout消费者3号
     * @Author: ColorXJH
     * @Date: 2022/9/2 13:21
     * @param msg
     * @Return: void
     **/
    /*@RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = RabbitCommonConstants.MODEL_EXCHANGE_FANOUT_NAME,type = ExchangeTypes.FANOUT),
            value = @Queue(value = RabbitCommonConstants.MODEL_QUEUE_NAME_FANOUT3)
    ),concurrency = "1-2")*/
    public void doFanoutConsumer3(String msg){
        System.out.println("--doFanoutConsumer3--:"+msg);
    }

    /**
     * Description:  bindings-队列绑定关系 exchange-匹配交换器名称类型 value-绑定的队列名称 key-路由键对应规则
     * @Author: ColorXJH
     * @Date: 2022/9/2 14:15
     * @param msg
     * @Return: void
     **/
    /*@RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = RabbitCommonConstants.MODEL_EXCHANGE_TOPIC_NAME,type = ExchangeTypes.TOPIC),
            value = @Queue(value = RabbitCommonConstants.MODEL_QUEUE_INFO_TOPIC,autoDelete = "true"),
            key="model.queue.*.topic"
    ))*/
    public void doTopicInfoConsumer(String msg){
        System.out.println("--doTopicInfoConsumer--:"+msg);
    }
    //@RabbitHandler
    //@RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用；
    //@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型

    //@QueueBinding 声明队列依据路由规则绑定交换器
    //@Exchange声明交换器的一些基本参数和常用配置，默认类型direct、默认声明（declare = true）
    //@Queue 声明队列的一些基本参数和常用配置，默认声明（declare = true）
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = RabbitCommonConstants.MODEL_EXCHANGE_TOPIC_NAME,type = ExchangeTypes.TOPIC),
            value = @Queue(value = "doTopicErrorConsumer",autoDelete = "true"),
            key="model.queue.#"
    ))
    public void doTopicErrorConsumer(String msg){
        System.out.println("--doTopicErrorConsumer--:"+msg);
    }

    //topic交换器，从样例打印的log可以看出，样例中的一个消费者可以消费了不同队列的消息，其实topic交换器是通过路由key的匹配规则来实现更高灵活度的模式；
    //匹配标识符：* 表示单个，# 表示匹配多

    //性能：fanout > direct > topic

    @RabbitListener(queues = "model.queue.name.topic123")
    public void testTopic123(Map<String,Object> map){
        System.out.println(map);
    }
}
