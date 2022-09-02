package com.example.springbootrabbitmq.config;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 交换器、队列、路由键 名称
 * @date 2022/9/2 12:40
 */
public  interface RabbitCommonConstants {
    /**
     * direct交换器名称
     */
    String MODEL_EXCHANGE_DIRECT_NAME = "model.exchange.direct.name";

    /**
     * fanout交换器名称
     */
    String MODEL_EXCHANGE_FANOUT_NAME = "model.exchange.fanout.name";

    /**
     * topic交换器名称
     */
    String MODEL_EXCHANGE_TOPIC_NAME = "model.exchange.topic.name";

    /**
     * 空字符串常量表示
     */
    String COMMON_EMPTY_STRING = "";

    /**
     * 直连交换器队列
     */
    String MODEL_QUEUE_NAME_DIRECT1 = "model.queue.name.direct1";

    /**
     * 直连交换器队列
     */
    String MODEL_QUEUE_NAME_DIRECT2 = "model.queue.name.direct2";

    /**
     * fanout的队列名称
     */
    String MODEL_QUEUE_NAME_FANOUT1 = "model.queue.name.fanout1";

    /**
     * fanout的队列名称
     */
    String MODEL_QUEUE_NAME_FANOUT2 = "model.queue.name.fanout2";

    /**
     * fanout的队列名称
     */
    String MODEL_QUEUE_NAME_FANOUT3 = "model.queue.name.fanout3";

    /**
     * topic的队列名称
     */
    final static String MODEL_QUEUE_INFO_TOPIC = "model.queue.info.topic";

    /**
     * topic的队列名称
     */
    final static String MODEL_QUEUE_ERROR_TOPIC = "model.queue.error.topic";

}
