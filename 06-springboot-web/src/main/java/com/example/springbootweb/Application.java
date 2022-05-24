package com.example.springbootweb;

import com.example.springbootweb.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

//web开发  SpringBoot中，SpringMVC的web配置都在 WebMvcAutoConfiguration 这个配置类里面；
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
                //https://www.cnblogs.com/jusha/p/11979734.html
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



//2:国际化
    //springmvc使用步骤
        //1:编写国际化配置文件
        //2:使用ResourceBoundleMessageSource管理国际化资源文件
        //3:在页面使用fmt:message(jsp页面标签)
    //springboot使用步骤
        //1：编写国际化配置文件,抽取页面需要编写的国际化消息(默认文件，语言代码_国家代码.properties 配置文件)
        //2:springboot自动配置好了管理国际化资源文件的组件
            //注意private String basename = "messages";我们的配置文件可以直接放在类路径下叫messages.properties的文件
            /*@Configuration(
                    proxyBeanMethods = false
            )
            @ConditionalOnMissingBean(
                    name = {"messageSource"},
                    search = SearchStrategy.CURRENT
            )
            @AutoConfigureOrder(-2147483648)
            @Conditional({org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration.ResourceBundleCondition.class})
            @EnableConfigurationProperties
            public class MessageSourceAutoConfiguration {*/


            /*@Bean
            public MessageSource messageSource(MessageSourceProperties properties) {
                ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
                if (StringUtils.hasText(properties.getBasename())) {
                    messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(properties.getBasename())));
                }*/
        //3:去页面获取国际化的值
            //原来的jsp页面使用fmt:message标签
            //新的模板引擎thymeleaf:MessageExpression表达式为： #{}
                //idea可以设置本项目和全局的文件编码以及jdk等结构等，本项目的在setting,以及project structure里面，全局的在setting-》 new project setrup
                //里面的settings 和structure里面
        //4:效果：根据浏览器语言设置的信息切换了国际化
            //原理：jav国际化Local(区域信息对象)，springmvc中的组件LocalResolver（获取区域信息对象），springboot继承springmvc中的各种resolver,其中就有一个叫做
            //LocaleResolver,默认帮我们配置了这个区域信息解析器，这个默认的区域信息解析器是根据请求头（request.getLocale()）带来的区域信息获取Locale进行国际化
        //5:如果需要根据按钮自动切换，需要自己写一个区域信息解析器(MyLocaleResolver)

        //3:登录
            //模板引擎页面修改以后，要实时生效
               //1):禁用模板引擎的缓存  spring.thymeleaf.cache=false
               //2):页面修改完成后ctrl+F9,重新编译
            //登录错误消息的显示
                //<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
            //拦截器进行登录检查（如果不做，那么直接访问目标url就可以直接访问到资源从而绕过登录页面）

        //4:crud员工列表
            //实验要求：1：restful crud     uri满足： /资源名称/资源标识  请求方式：http请求方式实现对资源得crud操作
            //剧烈： 删除 普通请求：deleteEmp?id=1   restful: emp/{id}--delete请求方式
            //员工列表：thymeleaf公共页面元素抽取
                //抽取公共片段：th:fragment="topbar"
                //重用：引入公共片段：<div th:insert="~{commons/bar::topbar}"></div> ==》<div th:insert="commons/bar::topbar"></div>
                    //模板名：：选择器  或者 模板名::片段名
                    //insert replace include 三种都可以抽取  1：将公共片段整个插入到元素中 2：将声明元素替换为公共片段 3：将被引入得片段内部得内容插入到元素中
                    //行内写法需要加上波浪[[~{}]], [(~{})]
        //引入片段的时候传入参数

        //错误处理机制
            //1:springboot默认的错误处理机制
                //浏览器：返回一个默认的错误页面
                //如果是其他客户端，默认响应json数据
            //2:原理可以参照：ErrorMvcAutoConfiguration:错误处理的自动配置、
                //给容器中添加了接个重要的组件
                    //1:DefaultErrorAttributes
                        //帮助我们在页面共享信息
                    //2:BasicErrorController
                        //@RequestMapping({"${server.error.path:${error.path:/error}}"}),处理默认/error请求
                    //3:ErrorPageCustomer
                        //private String path="/error",系统出现错误后来到error请求进行处理，=》，类似于servlet的web.xml注册的错误页面规则
                    //4:DefaultErrorViewResolver
                //步骤：一但出现4XX或者5XX之类的错误，ErrorPageCustomer就会生效（定制错误的响应规则）,就会来到/error请求，就会被BasicErrorController处理；
                    //errorHtml/error方法分别产生html数据和json数据（浏览器发送请求的请求头有accept：text/html,其他客户端：Accept: */*，所以springboot会区分）
                    //1:响应页面：去哪个页面是由DefaultErrorViewResolver得到的-》默认springboot去找页面error/viewname(error/404)
                        //如果模板疫情可用就会使用模板疫情解析，返回到errorViewName指定的视图地址
                        //如果模板疫情不可用，就在静态资源文件夹下寻找viewName对应的页面
        //2:如何定制错误响应
             //1:如何定制错误页面
                //1:有模板引擎的情况下：error/状态码 （将错误页面命名为错误状态码.html 放在模板引擎文件夹的error文件夹下），发生次状态码的错误，就会来到对应的页面，
                            //所有4XX/5XX的错误都能匹配，所以4XX/5XX.html页面
                            //2:页面能获取的信息
                            //timestamp 时间戳  status 状态码 error 错误提示  exception 异常对象 message 异常消息 errors jsr303数据校验的错误都在这里（BindingResults）
                //2:没有模板引擎的情况下（模板引擎找不到这个页面，静态资源文件夹下查找 static）
                //3:以上都没有错误页面，默认来到springboot的错误提示页面
            //2:如何定制错误的json数据（在异常处理器中处理）
                /*自定义json 返回数据 json格式 @ResponseBody*//*
                 @ResponseBody
                    @ExceptionHandler(MyException.class)
                    public Map<String,Object> myHandlerException(Exception e){
                        Map<String,Object> map=new HashMap<>();
                        map.put("code","user not exist");
                        map.put("message",e.getMessage());
                     return map;
                    }*/
                    //缺点：没有自适应效果 浏览器、客户端都是json数据
                //如何将我们自己写的数据携带出去：
                    //1：出现错误后，会来到/error请求，会被BasicErrorController处理，响应出去可以获取的数据是由getErrorAttributes得到的
                    //(是AbstractErrorController(ErrorController)规定的方法)
                    //编写一个errorController实现类或者继承了AbstractErrorController的类，重写两个方法，error/errorHtml，在方法中自定义需要返回的数据

                    //2:页面上能用的数据，或者是json返回能用的数据，都是通过errorAttributes.getErrorAttributes得到
                        //容器中DefaultErrorAttributes.getErrorAttributes()默认进行数据处理的，
                        //自定义ErrorAttributes
                    /*@Component
                    public class MyErrorAttributes extends DefaultErrorAttributes {

                        //返回的map就是页面和json能获取到的所有字段
                        @Override
                        public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                            Map<String, Object>map=super.getErrorAttributes(webRequest,options);
                            map.put("company","colorXJH");
                            //我们的异常处理器携带的数据
                            Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
                            map.put("ext",ext);
                            return map;
                        }
                    }*/
                    //在异常处理器中将需要的数据通过request传递进去
                    //为了达到自适应效果,可以通过定制ErrorAttrributes改变需要返回的内容
                    /*@ExceptionHandler(MyException.class)
                    public String myHandlerException(Exception e, HttpServletRequest request){
                        Map<String,Object> map=new HashMap<>();
                        //传入我们自己的错误状态码 4xx/5xx,否则就无法进入错误界面的处理流程
                        request.setAttribute("javax.servlet.error.status_code",500);
                        map.put("code","user not exist");
                        map.put("message","MY MESSAGE");
                        request.setAttribute("ext",map);
                        //转发到/error
                        return "forward:/error";
                    }*/

                    //http://localhost:8080/crud/myhello?user=aaa
