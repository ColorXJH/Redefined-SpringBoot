package com.example.springbootsecurity.config;

import com.example.springbootsecurity.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/8 15:09
 */
@Configuration
@EnableWebSecurity    // 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 启用方法级别的权限认证
public class SecurityConfig {
    /**
     * 密码明文加密方式配置
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //注意：原先继承WebSecurityConfigurerAdapter类重写configure方法已被废弃
    //而是注入一个过滤链的Bean,通过这个过滤链去处理用户登录的请求;
    //该过滤链返回值为SecurityFilterChain(接口)的实体类
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http.authorizeRequests()
                //角色权限配置,也可以使用注解
                //.antMatchers("/user/**").hasAnyRole("user")
                //.antMatchers("/admin/**").hasAnyRole("admin")
                //登录配置
                .antMatchers("/doLogin","/logins","/static/*/**").permitAll() //和表单登录相关的接口以及静态资源统统都直接通过
                //其他路径一律验证
                .anyRequest().authenticated()
                .and()
                //登出配置
                .logout()
                .logoutUrl("/logout1")
                .logoutSuccessUrl("/login").and()
                .formLogin()
                //指定登录页的路径
                .loginPage("/logins")
                //指定自定义form表单请求的路径(逻辑默认springsecurity处理，当然我们也可以自己处理用户数据校验)
                .loginProcessingUrl("/doLogin")
                //验证失败跳转路径
                .failureUrl("/showFail")
                //登录成功后的跳转路径
                .defaultSuccessUrl("/showMain")
                //.failureForwardUrl("/showFail")
                //.successForwardUrl("/showMain")
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问这个page。
                .permitAll()
                //设置记住我--基于token
                .and().rememberMe()
                .key(rememberMeKey)
                .rememberMeServices(rememberMeServices())
                //记住我-数据库持久化功能
                /*.and().rememberMe()
                .userDetailsService(userDetailsService())
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)*/
                .and().csrf().disable().build();

        /*return http
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();*/
    }
    //更改默认的用户名和密码
    //https://www.yisu.com/zixun/720908.html
   /* @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("ColorXJH").password("$2a$10$x.yYZd3TQFCw.QPUGZXP7etbMyc0ixBcjz8G93jb9vyHm8s5NPK..").roles("user").build());
        return userDetailsManager;
    }*/

    @Bean
    UserDetailsService userDetailsService(){
        return new UserDetailServiceImpl();
    }

    //关于记住我，他们都有一个公共的RememberMeServices接口，对应不同的实现，一种是
        //1:TokenBasedRememberMeServices:简单的token加密服务，不安全们获取到
        //2:PersistentTokenBasedRememberMeServices:持久化的token服务

    /** 方式1 :https://blog.51cto.com/u_11142439/3078349
     * 记住我功能，持久化的token服务
     * @return
     */
    //@Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //数据源设置
        tokenRepository.setDataSource(dataSource);
        //启动的时候创建表，这里只执行一次，第二次就注释掉，否则每次启动都重新创建表
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    //数据源
    @Resource
    private DataSource dataSource;


    /** 方式2:https://blog.51cto.com/u_11142439/3078370
     * 记住我功能，基于简单加密token的方案
     * @return
     */

    /** 用来防止token被修改的key */
    private String rememberMeKey = "ColorXJH";
    @Bean
    public RememberMeServices rememberMeServices() {
        TokenBasedRememberMeServices tbrms = new TokenBasedRememberMeServices(rememberMeKey, userDetailsService());
        // [可选]需要配置cookie的过期时间，默认过时时间1209600秒，即2个星期。这里设置cookie过期时间为60秒
        tbrms.setTokenValiditySeconds(60*3);
        // 设置checkbox的参数名为rememberMe（默认为remember-me），
        //注意如果是ajax请求，参数名不是checkbox的name而是在ajax的data里
        //tbrms.setParameter("rememberMe");
        return tbrms;
    }
}

