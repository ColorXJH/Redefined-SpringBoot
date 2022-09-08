package com.example.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
        /*return http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/showMain")
                .failureForwardUrl("/showFail")
                .and().authorizeHttpRequests()
                .antMatchers("/showLogin","login.html").permitAll()
                .antMatchers("/showFail","fail.html").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/user/**").hasAuthority("user")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()//基于 token，不需要 csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 基于 token，不需要 session
                .and().build();*/

        return http
                .authorizeHttpRequests(auth->auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
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
