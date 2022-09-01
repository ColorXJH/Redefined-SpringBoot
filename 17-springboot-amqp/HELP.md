# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#data.sql)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#using.devtools)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#messaging.amqp)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

# springboot与消息
1. JMS
2. AMQP
3. RabbitMQ

### 概述
1. 大多数应用中，可以通过消息服务中间件来提升系统异步通信，扩展解耦能力
2. 消息服务中两个重要概念：
   * 消息代理（message broker）
   * 目的地（destination）
> 当消息发送者发送消息后，将由消息代理接管，消息代理保证消息传递到指定目的地
3. 消息队列主要有两种形式的目的地：
   * 队列（queue）：点对点消息通信（point-to-point）
   * 主题（topic）：发布（publish）/订阅（subscribe）消息通信
4. 点对点式；
   * 消息发送者发送消息，消息代理将其放入一个队列中，消息接收者从队列中获取消息内容，
消息读取后被移出队列
   * 消息只有唯一的发送者和接受者,但不是说只能有一个接收者
5. 发布订阅式：
   - 发送者（发布者），发送消息到主题，多个接收者（订阅者）监听（订阅）这个主题，那么就会在消息同时
收到消息
6. JMS:(Java Message Service)Java消息服务
   * 基于JVM消息代理的规范，ActiveMQ,HornetMQ是JMS的实现
7. AMQP(Advanced Message Queuing Protocol)
   * 高级消息队列协议，也是一个消息代理的规范，兼容JMS
   * RabbitMQ是AMQP的实现
8. 对比JMS与AMQP
>  JMS定义了java api 层面的标准，在java体系中，多个client均可以通过JMS进行交互
不需要应用修改代码，但是其对跨平台的支持较差，AMQP定义了wire-level（网络线级协议）层的协议标准
天然具有跨平台，跨语言特性
9. spring支持
   * spring-jms提供了对JMS的支持
   * spring-rabbit提供了对AMQP的支持
   * 需要ConnectionFactory的实现来连接消息代理
   * 提供JmsTemplate,RabbitTemplate来发送消息
   * @JmsListener(JMS),@RabbitListener(AMQP)注解在方法上监听消息代理发布的消息
   * @EnableJms,@EnableRabbit开启支持
10. springboot自动配置
    * JmsAutoConfiguration
    * RabbitAutoConfiguration  

11. 


 

