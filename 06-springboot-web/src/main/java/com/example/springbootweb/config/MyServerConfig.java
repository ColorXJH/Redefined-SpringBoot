package com.example.springbootweb.config;

import com.example.springbootweb.filter.MyFilter;
import com.example.springbootweb.listener.MyListener;
import com.example.springbootweb.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

/**
 * @ClassName: MyServerConfig
 * @Package: com.example.springbootweb.config
 * @Description:
 * @Datetime: 2022/5/25 15:23
 * @author: ColorXJH
 */
@Configuration
public class MyServerConfig {
    //配置嵌入式的servlet容器
    //public EmbeddedServletContainerCustomizer customizer;
    //1,5使用上面的定制器，2.0使用下面的定制器
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> embbededServletContainerCustomizer(){
        //匿名内部类
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>(){
            //alt+insert重写方法
            @Override
            //双击类+F4=》打开类的继承树·
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8085);
            }
        };
    }


    //注册三大组件
    @Bean
    public ServletRegistrationBean<Servlet> myServlet(){
        ServletRegistrationBean<Servlet>  registrationBean=new ServletRegistrationBean<Servlet>(new MyServlet(),"/myServlet");
        //设置启动时顺序
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter>myFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener>myListener(){
        ServletListenerRegistrationBean<EventListener> listener=new ServletListenerRegistrationBean(new MyListener());
        return listener;
    }


}
