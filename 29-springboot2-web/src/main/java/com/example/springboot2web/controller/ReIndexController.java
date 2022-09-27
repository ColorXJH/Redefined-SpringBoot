package com.example.springboot2web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/21 10:04
 */
@Controller
public class ReIndexController {
    /*以下两个方法都可以重写，一个是请求转发，另一个是跳转到对应模板*/
    /*@GetMapping("/")
    public String newIndex(){
        return "forward:hello.html";
    }*/
    @GetMapping("/")
    public String getIndex(){
        return "login";//默认static目录下的login.html
    }


    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request){
        request.setAttribute("msg","hello-world");
        request.setAttribute("code","java");
        return "forward:/success";//转发到/success请求
    }

    @GetMapping("/success")
    @ResponseBody
    public Map success(@RequestAttribute("msg") String msg,
                          @RequestAttribute("code") String code,
                          HttpServletRequest request){
        Object o=request.getAttribute("msg");
        Map<String,Object> map=new HashMap<>();
        map.put("msg",msg);
        map.put("code",code);
        map.put("o",o);
        return map;
    }
    @GetMapping("/success2")
    public String success2(Model model){
        //model数据会被放在请求域中==request.setAttribute()
        model.addAttribute("msg","HELLO-WORLD");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }
}
