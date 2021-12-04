package com.example.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//web开发
    //1：创建springboot应用，选中我们需要的模块
    //2：springboot已经默认将这些场景都配置好了，只需要在配置文件中指定少量配置就可以运行起来
    //3：自己编写代码
        //自动配置原理;这个场景springboot帮我们配置了什么，能不能修改？，能修改哪些配置，能不能扩展？
        //xxxAutoConfiguration类：帮我们给容器中自动配置组件
        //xxxProperties类：配置类来封装配置文件的类容
//springboot对静态资源的映射规则,
    //1:所有/webjars/**下的所有请求，都去classpath:/META-INF/resources/webjars/下找资源
        //webjars:以jar包的方式引入静态资源：http://www.webjars.org
        //MacBook截图快捷键：command+shift+4    command+shift+3
        //localhost:8080/webjars/jquery/3.3.1/src/jquery.js
    //2：静态资源的文件夹：private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};
        //Java包下和resources文件夹下都是类路径的根路径
            //localhost:8080/new1.css   关于static文件夹下的静态文件不生效的原因：https://www.jb51.net/article/175230.htm
                    //讲道理其实springboot帮我们自动配置了这些静态文件的路径位置
                    //当@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})这个条件满足时会生效
    //欢迎页：静态资源下的所有index.html页面，都被/**映射
    //低版本的**/favicon.ico都是在静态资源文件夹下找，高版本可能带来安全风险，删除掉了(但是貌似放在类文件的根路径下同时ctrl+f5刷新一下也可以出来)


//3:模版引擎
    //JSP，Velocity, Freemarker, Thymeleaf
    //springboot推荐使用thymeleaf,语法更简单，功能更强大，
    //<dependency>
        //<groupId>org.springframework.boot</groupId>
        //<artifactId>spring-boot-starter-thymeleaf</artifactId>
    //</dependency>
    //可以在<properties>标签中切换thymeleaf和其布局功能的版本
    //<thymeleaf.version>3.0.12.RELEASE</thymeleaf.version>
    //<thymeleaf-layout-dialect.version>3.0.0</thymeleaf-layout-dialect.version>
        //默认thymeleaf3适配layout-dialect 2   thymeleaf2适配layout-dialect1版本
    //thymeleaf使用以及语法
        //public static final String DEFAULT_PREFIX = "classpath:/templates/";
        //public static final String DEFAULT_SUFFIX = ".html";
    //<html lang="en" xmlns:th="http://www.thymeleaf.org">导入thymeleaf的命名空间

//语法规则：详情简thymeleaf官网文档
    //1：th:text :改变当前元素的文本内容
        //th:任意html属性 来替换html原声属性
    //2：表达式语法



