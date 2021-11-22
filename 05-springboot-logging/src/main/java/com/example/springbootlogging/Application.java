package com.example.springbootlogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
//jdbc--数据库驱动
    //写一个同一的接口层，日志门面（日志的一个抽象层）
    //给项目中导入具体的日志实现就行了，具体日志框架实现抽象层
//市面上的日志框架
    //JUL JCL Jboss-logging logback log4j log4j2 slf4j
    //日志门面：JCL  SLF4J JBoss-logging    //日志实现 log4j  JUL  log4j2  logback    门面+实现==》SLF4J+logback
//springboot:底层是spring框架，spring框架默认使用JCL(jakarta commons logging),springboot选用slf4j+logback
    //如何再系统中使用slf4j:在开发的时候，日志记录，调用日志记录方法，不应该直接调用实现类，而是调用抽象层的方法
        //1:系统导入slf4j的jar包和logback的实现jar ==>http://www.slf4j.org/manual.html
//每一个日志的实现框架都有自己的配置文件，使用slf4j之后，配置文件还是做成日志实现框架的配置文件;
    //遗留问题：a(slf4j+logback)：Spring(commons-logging)  Hibernate(jboss-logging)  Mybatis XXX...
    //统一日志记录，即使是别的框架和我们一起，统一使用slf4j进行输出
        //1：先将系统中其他日志框架排除，
        //2：用中间包来替换原有的日志框架
        //3：我们再来导入slf4j其他的实现
//springboot 依赖一下组件实现日志功能
/*
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-logging</artifactId>
<version>2.6.0</version>
<scope>compile</scope>
</dependency>
*/
//springboot底层也是使用slf4j+logback方式来记录日志
//springboot也把其他的日志都替换成的slf4j==>jul-to-slf4j,log4j-over-slf4j
//如果我们要引入其他框架，一定要把这个框架的默认日志依赖移除掉
    //spring框架使用的是commons-logging，所以springboot引入spring的时候派出了它内部的commons-logging依赖，springboot能适配所有的日志，底层使用slf4j+logback，唯一需要注意的是
    //在引入其他框架的时候需要将这个框架依赖的日志排除掉
/*
<dependency>
<groupId>org.apache.activemq</groupId>
<artifactId>activemq-console</artifactId>
<version>${activemq.version}</version>
<exclusions>
<exclusion>
<groupId>commons-logging</groupId>
<artifactId>commons-logging</artifactId>
</exclusion>
</exclusions>
</dependency>
*/

//默认配置:springboot默认帮我们配置好了日志
//指定配置：给类路径下放上每个日志框架自己的配置文件即可，springboot就不使用默认配置了，
    //关于springboot的配置规则文件命名，可以查看springboot官方文档，有一节详细介绍了log配置文件
    //logback.xml直接被日志框架识别 logback-spring.xml被springboot加载识别，可以使用springboot的部分高级功能，可以指定某段配置只在某个环境下生效,springProfile