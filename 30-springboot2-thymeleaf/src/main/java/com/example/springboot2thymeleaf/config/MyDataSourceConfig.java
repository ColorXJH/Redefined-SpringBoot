package com.example.springboot2thymeleaf.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/8 9:45
 */
@Deprecated //过时
//@Configuration
//@ImportResource(locations = {"classpath:beans.xml"})//加载外部资源
public class MyDataSourceConfig {
    /*
     这里的druid使用的是alibaba的druid-spring-boot-starter，而不是传统的druid
     传统的druid没有datasource实例，需要我们自己配置，不推荐。
     因为 druid-spring-boot-starter 依赖提供了 DruidDataSourceBuilder 类，
     这个可以用来构建一个 DataSource 实例
     */

    @Bean
    //设置自定义数据源的地址用户名密码url等等，从配置文件中获取（因为参数是一样的）
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        //加入druid sql监控功能，防火墙功能
        druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    //放置druid的监控页面servlet
    //localhost:port/druid
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        statViewServletServletRegistrationBean.addInitParameter("loginUsername","ColorXJH");
        statViewServletServletRegistrationBean.addInitParameter("loginPassword","123456");
        return statViewServletServletRegistrationBean;
    }

    //WebStatFilter用于采集web-jdbc关联监控的数据
    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter filter=new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterFilterRegistrationBean=new FilterRegistrationBean<>(filter);
        /*拦截所有请求*/
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        /*放行静态资源以及/druid/*下的所有请求（监控页的需要放心）*/
        filterFilterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterFilterRegistrationBean;
    }
    //配置_Druid和Spring关联监控配置
    //import导入资源
    //详细参见：https://dandelioncloud.cn/article/details/1487429206568194049
    //也可以不使用或者配置注解类，使用配置文件+druid-spring-boot-starter也可以实现这些功能

}
