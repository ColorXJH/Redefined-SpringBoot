package com.example.springbootjdbc.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName: DruidConfig
 * @Package: com.example.springbootjdbc.config
 * @Description:
 * @Datetime: 2022/6/6 22:09
 * @author: ColorXJH
 */
@Configuration
public class DruidConfig {
    /*
     * 功能描述:为我们的Druid的datasource配置属性，并且创建该类型bean注入到容器中使用
     * @param: null
     * @Return:
     * @Author: ColorXJH
     * @Date: 2022/6/6 22:11
     */
    //@ConfigurationProperties(prefix = "druid.stat")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
       return  new DruidDataSource();

    }


    /*
     * 功能描述:配置Druid的监控
     * 1：配置一个管理后台的servlet
     * 2：配置一个监控的filter
     * 注意：一下配置可以在springboot2中使用yml进行配置，不用再写配置类了，只需要在pom文件中配置一个druid-springboot-starter
     */
    //1：配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String>initParams=new HashMap<String,String>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
        initParams.put("deny","192.168.1.13");
        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }

    //2：配置一个监控web的filter
    @Bean
    public FilterRegistrationBean webStatusFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        Map<String,String>initParams=new HashMap<String,String>();
        initParams.put("exclusions","*.js,*.css,/druid/*");//哪些不拦截
        filterRegistrationBean.setInitParameters(initParams);//可以设置filter的一些初始化参数
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));//拦截所有请求
        return filterRegistrationBean;
    }


}
