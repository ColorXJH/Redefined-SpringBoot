package com.example.springbootweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyMvcConfig
 * @Package: com.example.springbootweb.config
 * @Description:
 * @Datetime: 2022/2/21 20:32
 * @author: ColorXJH
 */
//idea中查找类：右上方放大镜搜索按钮，或者双击shift按钮弹出
//使用WebMvcConfigurerAdapter可以扩展springmvc得功能，基于接口WebMvcConfigurer实现得空实现，自定义配置
//@EnableWebMvc //全面接管后，所有的springmvc的自动配置都失效，一些静态资源的页面也就无法访问（只是体现之一  ）
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private MyIntercepter myIntercepter;
    //ctrl+o  查看重写方法快捷键
    //alt+insert 重写方法得快捷键
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println(hello+"------------------------"+Arrays.toString(users));
        //super.addViewControllers(registry);
        //浏览器发送“/atHello”请求，也来到success页面,
        registry.addViewController("/atHello").setViewName("success");
    }

    //另一种方式添加页面跳转
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //springboot默认8080端口会跳转到静态资源文件下的首页（index.html页面），如果不在controller书写方法跳转到由模板引擎接管的login页面
                //在这里也可以实现跳转
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                //登录成功以后防止表单重复提交，可以设置重定向
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //添加拦截器(这个拦截器目前有问题)
                //registry.addInterceptor(myIntercepter).addPathPatterns("/**");
                    //ok
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
                //   registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                //   .excludePathPatterns("/index.html","/","/user/login");

                //扩展配置loging拦截器
                registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")// /++表示任意路径下的任意请求
                        .excludePathPatterns("/index.html","/","/user/login");//排除拦截请求(注意这里如果自定义了项目访问路径必须要带上)
                //静态资源如何放行不拦截？以前在springmvc的时候需要放行，但是springboot已经做好了静态资源映射，所以我们不需要处理
            }
        };
        return adapter;
    }
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Value("${hello}")
    private String hello;
    @Value("${users}")
    private String[] users;
}


           //2:
           //如果继承WebMvcConfigurationSupport 这个类就会导WebMvcAutoConfiguration失效，具体可以看这个类上面的条件，会全盘接管web，
           //如果不想全盘接管，可以实现WebMvcConfigurer这个接口，这样原本的默认配置也有，你加的扩展配置也成立

            //3:
            /*
            在SpringBoot 2之后的版本改用WebMvcConfigurer这个接口来注册controllers，和之前实现一样的功能，参考以下代码。
            @Configuration
            public class MyMvcConfig implements WebMvcConfigurer {
                @Override
                public void addViewControllers(ViewControllerRegistry registry) {
                    registry.addViewController("/").setViewName("login");
                    registry.addViewController("/index.html").setViewName("login");
                }
                //要排除一些静态资源，防止样式丢失
                @Override
                public void addResourceHandlers(ResourceHandlerRegistry registry) {
                    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                }
            }*/