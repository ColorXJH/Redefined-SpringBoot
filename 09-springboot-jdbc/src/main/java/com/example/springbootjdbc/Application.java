package com.example.springbootjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//springboot 与数据访问
    //jdbc              mybatis         springboot data jpa
//简介
    //对于数据访问层，无论是SQL还是NOSQL,springboot默认采用整合spring data的方式进行统一处理
    //添加了大量的自动配置，屏蔽了很多设置，引入各种xxxTemplate，xxxRepository来简化我们对数据访问层的操作
    //对我们来说只需要进行简单的设置即可，考虑使用SQL，NOSQL在缓存，消息，检索等方面测试

//整合基本的JDBC与数据源
    //1：引入starter=>spring-boot-starter-jdbc
    //2：配置application.yml
    //3：测试
    //4：高级配置：使用druid数据源
        //引入druid,配置属性
    //5：配置druid数据源监控
        //一下为如何安装docker以及在docker钟安装mysql
            //https://blog.csdn.net/cbh1987/article/details/120481157
            //https://blog.csdn.net/xtkinglong/article/details/123341676

        /*spring:
                datasource:
                username: root
                password: 2012WananXJH
                url: jdbc:mysql://192.168.40.128:3306/myjdbc
                driver-class-name: com.mysql.cj.jdbc.Driver*/
        //数据源的相关配置都在DataSourceProperties类中
        //使用的数据源为：com.zaxxer.hikari.HikariDataSource，详情见自动配置类：DataSourceAutoConfiguration
            //各种数据源配置参考如下类：DataSourceConfiguration，根据配置创建数据源，默认使用HiKAri
            //可以使用spring.datasource.type指定自定义的数据源类型
            //springboot默认支持tomcat.jdbc.pool.DataSource,HikariDatasource,dbcp.BasicDataSource
                //dbcp2,以及自定义的数据源类型
        /*@Configuration(
                proxyBeanMethods = false
        )
        @ConditionalOnMissingBean({DataSource.class})
        @ConditionalOnProperty(
                name = {"spring.datasource.type"}
        )
        static class Generic {
            Generic() {
            }

            @Bean
            DataSource dataSource(DataSourceProperties properties) {
                return properties.initializeDataSourceBuilder().build();
            }
        }*/
        //作用：运行建表语句，运行插入数据sql语句
        //默认将文件命名为 schema.sql, data.sql
    //操作数据库
        //自动配置了jdbcTemplate操作数据库
           // docker run -p 3306:3306  --name mysql03  -e MYSQL_ROOT_PASSWORD=2012WananXJH -d docker.io/mysql

