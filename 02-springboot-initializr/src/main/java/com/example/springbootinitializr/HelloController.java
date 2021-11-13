package com.example.springbootinitializr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ColorXJH
 * @CreateDate: 2021/11/13 21:15
 * @Version: 1.0
 */
/*@Controller
@ResponseBody*/ //这个类的所有方法返回的数据直接写给浏览器，如果是对象还能返回json数据
@RestController //等价于上面两个注解
public class HelloController {
    @RequestMapping("/hello-quick")

    public String hello(){
        return "hello-world-quick";
    }
}
