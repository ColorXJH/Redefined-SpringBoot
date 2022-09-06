# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)
* [Spring Data Elasticsearch (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#data.nosql.elasticsearch)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


## 检索
> 我们的应用经常需要添加检索功能，开源的elasticsearch是目前全文搜索引擎的首选
它可以快速的存储，搜索和分析海量数据，springboot通过整合spring-data-elasticsearch为我们
提供了非常便捷的检索功能支持
> elasticsearch是一个分布式的搜索服务，提供restfulAPI,底层基于Lucene,采用多shard(分片)
的方式保证数据安全，并且提供自动resharding的功能，github等大型网站应采用了elasticsearch作为其搜索服务
index/type/id