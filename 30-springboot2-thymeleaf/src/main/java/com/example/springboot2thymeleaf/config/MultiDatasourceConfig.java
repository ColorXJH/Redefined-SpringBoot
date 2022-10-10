package com.example.springboot2thymeleaf.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 多数据源配置，详情参考文章https://blog.csdn.net/qq_34491508/article/details/103854512
 * @date 2022/10/10 10:39
 */
@Deprecated
//@Configuration
public class MultiDatasourceConfig {
    //1:配置连接信息
    /*spring.datasource.one.url=jdbc:mysql:///test01?useUnicode=true&characterEncoding=utf-8
    spring.datasource.one.username=root
    spring.datasource.one.password=root
    spring.datasource.one.type=com.alibaba.druid.pool.DruidDataSource

    spring.datasource.two.url=jdbc:mysql:///test02?useUnicode=true&characterEncoding=utf-8
    spring.datasource.two.username=root
    spring.datasource.two.password=root
    spring.datasource.two.type=com.alibaba.druid.pool.DruidDataSource*/

    //2:配置数据源信息
    //如果mybatis需要配置多数据源，因为mybatis的自动配置中定义了当只有一个数据源的时候才启用
    //所以我们首先要两个数据源的properties配置，其次是在这里配置两个数据源 DataSource，类似如下
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    DataSource dsOne() {
        //return new DruidDataSource();//苏癌变选一个，看使用的而是哪个实现依赖
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    DataSource dsTwo() {
        return DruidDataSourceBuilder.create().build();
    }

    //3:MyBatis 的配置，
    // 因此这里两个数据源我将在两个类中分开来配置，分别为MyBatisConfigOne，MyBatisConfigTwo,详情请看配置类

}
