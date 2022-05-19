package com.example.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName: LoginController
 * @Package: com.example.springbootweb.controller
 * @Description:
 * @Datetime: 2022/5/19 15:28
 * @author: ColorXJH
 */
@Controller
public class LoginController {
    /**
     * 功能描述:
     * @param: username
     * @param: password
     * @param: map
     * @param: session
     * @Return: java.lang.String
     * @Author: ColorXJH
     * @Date: 2022/5/19 16:15
     */
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")//restful 风格    GetMapping PostMapping PutMapping DeleteMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, 
                        Map<String,Object>map,HttpSession session){
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            //登录成功以后防止表单重复提交，可以设置重定向，配合视图解析器重定向到dashboard页面
            session.setAttribute("loginUser",username);//只要登录了，用户就会在session中存在
            return "redirect:/main.html";
        }else{
            //登录失败
            map.put("msg","用户名密码错误");
            return "login";
        }
    }
}
