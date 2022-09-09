package com.example.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/8 14:14
 */
//@RestController //注意如果这里使用了@RestController，则默认返回字符串
@Controller //如果使用改注解，则默认返回视图解析器
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
        //return "/templates/hello.html"
    }

    @GetMapping("/showMain")
    public String showMain(){
        return "main";
    }

    @GetMapping("/showFail")
    public String showFail(){
        return "fail";
    }

    @PreAuthorize("hasAuthority('query')")
    @GetMapping("/user/user1")
    public String user(){
        return "user";
    }


    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin/admin1")
    public String admin(){
        return "admin";
    }


    @GetMapping("/logins")
    public String login(){
        return "login";
    }
}

/**
 *@RestController修饰的类会将 return后面的内容字符串的形式返回到@Requestmapping的页面
 *@Controller修饰的类会将return的字符串配合视图解析器InternalResourceViewResolver添加上前缀和后缀。跳转到对应的html页面。
 *@Controller和@ResponseBody配合作用和@RestController一样
 **/