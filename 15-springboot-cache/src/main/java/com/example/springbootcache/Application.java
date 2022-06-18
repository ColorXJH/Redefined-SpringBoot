package com.example.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//搭建基本环境
//1：导入数据库基本文件：可以参考10-springboot-mybatis资源文件sql中得数据文件sql,首先在本机创建好数据库
//2：创建javabean 封装数据库得数据
//3：整合mybatis操作数据库
    //1:配置数据源信息
    //2:使用注解版得mybatis
        //1:@MapperScan指定需要扫描的mapper接口所在得包
@MapperScan(value = "com.example.springbootcache.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}


//springboot与缓存
//jsr107
//spring缓存抽象
//整合redis
//缓存的应用：减少对数据库高频数据的访问，对临时数据的缓存，不需要放入到数据库中

//1:JSR107
//java caching定义了5个核心接口，分别是CachingProvider,CachingManager,Cache,
//Entry和Expiry
//CachingProvider:定义了创建、配置、获取、管理和控制多个CachingManager,一个应用可以在运行期间访问多个CachingProvider
//CachingManager:定义了创建、配置、获取、管理和控制多个唯一命名的Cache,这些Cache存在于CachingManager的上下文中，一个CachingManager
//仅被一个CachingProvider所拥有
//Cache:是一个类似Map的数据结构并临时存储以key为索引的值，一个Cache仅被一个CacheManager所拥有
//Entry:是一个存储在Cache中的key-value对
//Expiry:每一个存储在Cache中的条目有一个定义的有效期，一旦超过这个时间，条目为过期的状态，一旦过期，条目将不可访问、更新和删除
//缓存有效期可以通过ExpiryPolicy设置

//                  Application
//      CachingProvider        CachingProvider
//  CachingManager   CachingManager
// Cache  Cache  Cache
//  Entry<K,V>  Entry<K,V>
//  Expiry      Expiry
//如果要使用JSR107 需要导入依赖 javax-cache.cache-api依赖

//spring缓存抽象
//spring从3.1开始定义了org.springframework.cache.Cache和org.springframework.cache.CacheManager接口
//来统一不同的缓存技术，并支持使用JCache(jsr-107)注解简化开发
//Cache接口为缓存的组件规范定义，包含缓存的各种操作集合
//Cache接口下spring提供了各种xxxCache的实现，如：RedisCache,EhCacheCache,ConcurrentMapCache等
//每次调用需要缓存功能的方法时，spring会检查检查指定参数的指定的目标方法是否已经被调用过
//如果有就直接从缓存中获取该方法调用的结果，如果没有就调用方法并缓存结果后返回给用户，下次调用直接从缓存中获取
//使用spring缓存抽象时我们需要关注以下两点：
//1：确定方法需要被缓存以及他们的缓存策略
//2：从缓存中读取之前缓存的数据

//几个重要的概念以及缓存注解
//1：Cache:缓存接口，定义缓存操作，实现有：RedisCache EhCacheCache,ConcurrentMaoCache等
//2：CacheManager:缓存管理器，管理各种缓存（Cache）组件
//3：@Cacheable:主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
//4：@CacheEvict:清空缓存
//5：@CachePut：保证方法被调用，又希望结果被缓存
//6：@EnableCaching:开启基于注解的缓存
//7：keyGenerator:缓存数据时key生成策略
//8：serialize:缓存数据时value序列化策略