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

>注意：es的各个版本目前和springboot集成都不太统一，es7及其以下的可以使用springboot-data-elastic和Jest集成
> 在 Elasticsearch7.15版本之后，Elasticsearch官方将它的高级客户端 RestHighLevelClient标记为弃用状态。
> 同时推出了全新的 Java API客户端 Elasticsearch Java API Client，该客户端也将在 Elasticsearch8.0及以后版本中成为官方推荐使用的客户端。


> elasticsearch-rest-high-level-client在 7.15.0 中已弃用。
不推荐使用高级 REST 客户端，取而代之的是 Java API 客户端 。
spring-boot-starter-data-elasticsearch 也不推荐,虽然基础操作简化了很多,但是一旦使用了es高级特性,那么就如同进入了地狱,同时elasticsearch更新太快了spring-boot-starter-data-elasticsearch的版本根本就赶不上,导致升级会出现很多问题
现在在es官网推荐我们现在使用 Elasticsearch Java API 客户端 这个是相当于直接使用elasticsearch自身的接口Api所以不存在升级不兼容的问题
> 可见博客 https://blog.csdn.net/weixin_45203607/article/details/124369349