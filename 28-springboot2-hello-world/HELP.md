# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


## springboot一些注解

* @Configuration
> @Configuration 告诉springboot这是一个配置类===配置文件
> proxyBeanMethods方法默认为true,外部调用代理对象内部方法多次返回单实例对象
> Full/Lite:
> 配置类组件之间无依赖关系用Lite模式加速容器启动过程，减少判断
> 配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，用Full模式
* @Bean
> @Bean 标注这个类被IOC容器管理
* @ImportResource
> @ImportResource(locations = {"classpath:beans.xml"})//加载外部资源
* @Import(User.class,Pet.class)
> 给容器中自动创建组件(调用类的无参构造器)，默认组件的名字是全类名：com.xx.xx.Pig
* @Conditional
> 条件装备，有很多派生注解