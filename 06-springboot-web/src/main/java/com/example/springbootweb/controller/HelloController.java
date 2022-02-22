package com.example.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName: HelloController
 * @Package: com.example.springbootweb.controller
 * @Description:
 * @Datetime: 2021/11/25 22:02
 * @author: ColorXJH
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    @ResponseBody
    //返回json ,@ResponseBody+@Controller==@RestController
    public String Hello(){
        return "hello-world";
    }

    @RequestMapping("/success")
    //返回映射路径
    public String success(Map<String,Object>map){
        //classpath:/templates/success.html
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("张三","李四","王武","赵六"));
        return "success";
    }
    //注释掉，为了跳转而添加的方法
    /*@RequestMapping({"/","/index.html"})
    public String index(){
        return "login";
    }*/
}
