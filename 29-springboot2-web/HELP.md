# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#appendix.configuration-metadata.annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


## springboot静态资源配置
> 静态资源放在类路径下 /static /resources /public /META-INF/resources 下,都能够访问到
> 静态资源文件，在idea中，类路径指的是 根resources目录,
> 访问：当前项目的根路径/ +静态资源名，
> 原理：静态资源默认映射到/**，请求先进来，会先去Controller层看看有无对应的请求能否处理，不能处理的话，
> 请求交给静态资源处理器，如果静态资源处理器也找不到就会报404
> /webjars/**也被支持：http://localhost:8080/webjars/github-com-jquery-jquery/3.6.0/ajax/load.js

## 欢迎页支持
> 静态资源路径下 index.html   或者 controller 处理 /index请求
> 自定义访问页面，1：可以重写重写/映射方法，2：可以重新返回到对应的模板文件
> 详情见ReIndexController
## Custom Favicon
> 将图标命名为favicon.ico 并放置在静态资源路径下，刷新ctrl+F5 就能得到自定义图标

## spring配置bean中的参数都是默认去容器中拿