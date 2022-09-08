package com.example.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
                //角色权限配置
                .antMatchers("/user/**").hasAnyRole("user")
                .antMatchers("/admin/**").hasAnyRole("admin")
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
                .and().csrf().disable().build();

        /*return http
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();*/
    }
    //更改默认的用户名和密码
    //https://www.yisu.com/zixun/720908.html
    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("ColorXJH").password("$2a$10$Tsm7vu9gEaRHck/ZXwQH0.sDf5P7T9PGZsIoUWCAuF183FQxAxvKO").roles("user").build());
        return userDetailsManager;
    }
}
