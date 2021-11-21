package com.example.springbootconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationConfig {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }

}
//配置文件优先级顺序：从高到低
//file:/config/
//file:/
//classpath:/config/
//classpath:/
//如果你使用的是IDEA，新建的是一个Module.那么需要在Edit Configurations中，选中你需要配置的Module，并做如下修改:1:展开enviroment 2:在working directory栏输入$MODULE_DIR$

    //通过spring.config.location来改变默认的配置文件的位置
    //#使用外部的配置文件:项目打包好之后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置，指定配置文件和默认加载的这些配置文件会一起提供配置，形成互补配置
    //spring.config.location=E:\software\git\gitWorkSpace\application.properties(运维时使用)
    //java -jar .\springbootconfig-0.0.1-SNAPSHOT.jar --spring.config.location=E:\software\git\gitWorkSpace\application.properties


//外部配置的加载顺序
    //springboot支持多种外部配置方式（springboot也可以从以下位置加载配置，优先级从高到低，高优先级覆盖低优先级，共同配置）
    //这些方式的优先级如下：
        //1：命令行参数
        //2:来自java:comp/env的JNDI属性
        //3:java系统属性（System.getProperties()）
        //4:操作系统环境变量
        //5:RandomValuePropertySource配置的Random.*属性
        //6:jar包外部的application-{profile}.propertie或者application-{profile}.yml(带spring.proflie)配置文件（和jar包在同一级目录，启动时就使用外部的这个配置文件覆盖）
        //7:jar包内部的application-{profile}.propertie或者application-{profile}.yml(带spring.proflie)配置文件
        //8:jar包外部的application-{profile}.propertie或者application-{profile}.yml(不带spring.proflie)配置文件
        //9:jar包内部的application-{profile}.propertie或者application-{profile}.yml(不带spring.proflie)配置文件
        //10:@Configuration注解类上的@PropertySource
        //11:通过SpringApplication.setDefaultProperties指定的默认属性

//注意打成jar包的时候只能识别src/main目录下的文件夹，项目路径下的application.properties和项目config/application.properties文件时识别不出来的（不符合maven的目录结构）