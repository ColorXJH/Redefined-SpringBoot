# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#messaging.amqp)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

11. rabbitMQ简介
> rabbitMQ是一个由erlang开发的AMQP开源实现
* 核心概念
    - message
  > 消息，消息是不具名的，它由消息头和消息体组成，消息体是不透明的，而消息头则由一系列的可选项组成
  这些属性包括：routing-key(路由键)，priority(相对其他消息的优先级)，delivery-mode(指出该消息可能需要持久性的存储)等
    - publish
  > 消息的生产者，也是一个向交换器发布消息的客户端应用程序
    - Exchange
  > 交换器，用来接受生产者发送的消息并将这些消息路由给服务器中的消息队列
  Exchange有四种类型：direct(默认)，fanout,topic,headers不同类型的exchange转发消息的策略有所区别
    - Queue
  > 消息队列，用来保存消息直到发送给消费者，他是消息的容器，也是消息的终点，一个消息可以投入一个或者多个队列，消息一直在队列里面，等待消费者连接
  到这个队列将其取走
    - Binding
  > 绑定，用于消息队列和交换器之间的关联，一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个
  由绑定构成的路由表，Exchange和Queue的绑定可以是多对多的关系
    - Connection
  > 网络连接，比如一个TCP连接
    - Channel
  > 信道，多路复用连接中一条独立的双向数据楼通道，信道是建立在真是的TCP连接内虚拟连接，AMQP命令都是通过信道发送出去的，不管是发布消息还是订阅队列
  还是接受消息，这些动作都是通过信道完成的，因为对于操作系统来说建立和销毁TCP都是非常昂贵的开销，所以引入了信道的概念，以复用一条TCP连接
    - Consumer
  > 消息的消费者，表示从一个消息队列中取得消息的客户端应用程序
    - Virtual Host
  > 虚拟主机，表示一批交换机，消息队列和相关对象，虚拟主机是共享相同的身份认证和加密环境的独立服务器域，每个vhost本质上就是一个mini版的rabbitmq
  服务器，拥有自己的队列，交换机，绑定和权限机制，vhost是amqp概念的基础，必须在连接时指定，rabbitmq默认的vhost是 /
    - Broker
  > 表示消息队列服务器实体
> 总的关系图键resources/static文件夹下的 关系图.jpg   

## rabbitmq运行机制
  * AMQP中的消息路由
    > AMQP中消息的路由过程和java开发者熟悉的JMS机制存在一些差别，AMQP中增加了Exchange和Binding的角色
    生产者把消息发送到Exchange上，消息最终到达队列并被消费者接收，而Binding决定交换器的消息应该发送到哪个队列
    > 见/static目录下图：img.png
  * Exchange类型
    * headers:与direct完全一致，性能还差很多，目前几乎不用了，headers匹配AMQP消息的header而不是路由键
    * direct:消息中的路由键routing-key与Binding中的binding-key完全一致，转发到特定队列，完全匹配，单播
    * fanout:该exchange不处理路由键，简单将队列绑定到交换器上，类似子网广播，每台子网内的主机都获得了一份复制的消息
    该类型消息转发是最快的
    * topic:该交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式匹配，队列绑定到一个模式上，它将路由键和绑定键的字符串切分成单词
    这些单词之间用点号隔开，并识别两个通配符#匹配0或多个单词，*匹配一个单词
    > routing-key= user.name, user.weather... 
    binding-key=user.#,#.weather,*.weather...
    