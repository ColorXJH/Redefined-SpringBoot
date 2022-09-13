package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/13 13:12
 */
@RestController
@RefreshScope //开启属性更新功能，让这个bean里面的属性会根据配置中心的修改而同步
public class ConfigController {
    @Value("${foo:defalut}")
    private String foo;

    @Value("${name:defalut}")
    private String name;

    @GetMapping("/show1")
    public String show1(){
        return foo;
    }
    @GetMapping("/show2")
    public String show2(){
        return name;
    }
}
