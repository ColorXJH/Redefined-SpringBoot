package com.example.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String Hello(){
        return "hello-world";
    }
}
