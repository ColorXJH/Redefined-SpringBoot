package com.example.springbootweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //如何测试关于自定义的viewResolver,定义一个类实现viewResolver，并且配置成容器类然后springboot就将
    //这些类自动加载进入viewResolver列表中，在DispatcherServerlet类的doDispatch方法中打上断点，程序启动时
    //访问任意一个请求地址都会进入该方法，可再次方法的debug中找到我们的自定义viewResolver
    //@Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }
    private static class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
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
    //若是高项目版本中出现自定的项目路径
        //则请求时会因为也就是实际路径时http://域名+项目名+favicon.ico，路径错误，自然请求不到，由于谷歌的缓存机制，请求一次没请求到，后边不请求了，自然就没有了。
        /*可能出现不行的情况
        a.在application.properties下有配置相关的资源访问路径
        b.给静态资源定义了前缀*/
    //解决方法：1：如果没有设置项目路径名称，则可以在静态文件夹下使用资源，默认ctrl+f5刷新显示
            //2: 如果使用了自定义项目根路径，则可以使用页面明确指定ico：放在head标签中：<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
                //注意：这里是必须放在resources根路径下的资源文件/favicon.ico，
                //因为浏览器默认请求http://域名+favicon.ico，但是项目经常会配上项目名，也就是实际路径时http://域名+项目名+favicon.ico，路径错误，自然请求不到
                    //所以必须放在resources根路径下的资源文件/favicon.ico，
                //但是这样比较费劲，所有页面都要加，折中方案就是用母版页，统一添加，已有项目的话改动也比较大，
            //3:折中方案二：通过拦截器，在页面渲染完成后追加一段js，
            //可以继承HandlerInterceptor接口，重写afterCompletion方法，添加以下代码通过js写入
            /*String link = "<script>" +
                "var link = document.createElement('link');" +
                "link.type = 'image/x-icon';" +
                "link.rel = 'shortcut icon';" +
                "link.href = '/nascan/images/favicon.ico';" +
                "document.getElementsByTagName('head')[0].appendChild(link);" +
                "</script>";
            response.getWriter().append(link);*/
            //但是这个方法会有一个下问题，后台会报错：getOutputStream() has already been called for this response
                //因为：response.getWriter().append(link);这个方法和页面的out输出流冲突了
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

//springmvc 的自动配置
    //1：springboot自动配置好了springmvc,详情请见spring 的官方文档的springboot模块以及web模块的介绍
    //2：自动配置了xxxViewResolver:视图解析器，根据方法的返回值，得到视图对象（view）,视图对象决定是转发还是重定向以及如何渲染
        //ContentNegotiatingViewResolver
        //如何定制视图解析器：我们可以自己给容器中添加一个视图解析器，ContentNegotiatingViewResolver会自动将其组合进来
    //3:静态资源和webjars
    //4：静态首页访问index.html
    //5:convert转换器：类型转换使用  18(文本)-》integer
    //6：Formatter:格式化器 2021-12-01-》Date
    //7: httpMessageConverters:springmvc用来转换http请求和响应的：User--json;
        //底层是从容器中获取所有的HttpMessageConverters
        //自定义：自己添加converters,需要将组件注册到容器中（@Bean..）
    //8: messageCodeResolver定义错误代码生成规则
    //9：ConfigurableWebBindingInitializer：初始化WebBindingInitializer（web数据绑定器：请求参数===》javaBean）:initBinder
        //也可以自动配置
//扩展springmvc
    //<!--springmvc中的视图解析器-->
    //<mvc:view-controller path="/hello" view-name="success" />
    //<!--springmvc中的拦截器-->
    //<mvc:interceptors>
    //<mvc:interceptor>
    //<!--具体的拦截请求-->
    //<mvc:mapping path="/hello"/>
    //<!--具体的哪个拦截器-->
    //<bean></bean>
    //</mvc:interceptor>
    //</mvc:interceptors>
    //编写一个配置类（@Configuration注解标识的类），这个配置类的类型是WebMvcConfigurerAdapter,并且不能标注@EnableWebMvc注解
    //既保留了自动配置，也能用我们扩展的配置
        //原理：1：WebMvcAutoConfiguration是springMVC的自动配置类
            //2 WebMvcAutoConfiguration类中的内部类：WebMvcAutoConfigurationAdapter，他也继承了WebMvcConfigurerAdapter，主要的是其上方有一个注入注解
                //@Import({WebMvcAutoConfiguration.EnableWebMvcConfiguration.class}),这个类中有一个自动注入属性，表示了从容其中获取所有的WebMvcConfigurer
                    //@Autowired(
                    //        required = false
                    //)
                    //public void setConfigurers(List<WebMvcConfigurer> configurers) {
                    //    if (!CollectionUtils.isEmpty(configurers)) {
                    //        this.configurers.addWebMvcConfigurers(configurers);
                    //    }
                    //
                    //}
                //3：容器中的所有的WebMvcConfigurer都会一起起作用
                //4: 我们的配置类也会被调用
            //效果就是：springmvc的自动配置和我们的扩展配置都会起作用
        //@EnableWebMvc注解的作用是：全面接管springmvc,springboot对springmvc的自动配置不需要了，所有都是我们自己配
        //需要我们在配置类中添加注解@EnableWebMvc

        //原理：为什么加了@EnableWebMvc注解，自动配置就失效了
            //1：该注解的核心是导入了：@Import({DelegatingWebMvcConfiguration.class})，他导入了WebMvcConfigurationSupport类，导致配置类WebMvcAutoConfiguration的条件判断失效了
        /*@Configuration
        public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {*/
        //自动配置类的签名如下：
        /*@Configuration(
                proxyBeanMethods = false
        )
        @ConditionalOnWebApplication(
                type = ConditionalOnWebApplication.Type.SERVLET
        )
        @ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
        @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
        @AutoConfigureOrder(-2147483638)
        @AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
        public class WebMvcAutoConfiguration {*/
        //其中包含条件判断： @ConditionalOnMissingBean({WebMvcConfigurationSupport.class})，没有这个类才生效
        //导入的WebMvcConfigurationSupport只是springmvc最基本的功能

//如何修改springboot的默认配置
    //1：springboot在配置组件时，先看用户是否自己配置了，如果有就用用户配置，如果没有，就自动配置（@ConditionalOnMissingBean...）
    //2：@Configuration 增强配置
    //3:在springboot中会有非常多的xxxConfigurer类帮助我们扩展配置，









