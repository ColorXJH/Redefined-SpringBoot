package com.example.springbootinitializr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class Application {
    //使用idea的initializr生成，帮助我们生成了resources文件夹结构 static放静态资源 templates 保存所有模板页面（springboot默认jar包使用嵌入式tomcat,默认不支持jsp页面，可以使用模板引擎）
        //freemarker thymeleaf
    //application.properties:springboot应用的配置文件,或者使用application.yml,作用是修改springboot自动配置的默认值
    //配置文件放在src/main/resources目录或者 类路径/config下
        //xml <server><port>8080</port></server>
        //yml  key:(空格)value
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//配置文件加载位置
    //1:springboot启动会扫描一下位置的application.properties或者application.yml文件作为springboot的默认配置文件
    //file:./config/
    //file:./
    //classpath:/config/
    //classpath:/
    //以上是按照优先级从高到低排序的，所有位置的文件都会被加载，高优先级配置的内容会覆盖低优先级配置的内容
    //我们也可以通过配置spring.config.location来改变默认配置，见新工程：03-springboot-config