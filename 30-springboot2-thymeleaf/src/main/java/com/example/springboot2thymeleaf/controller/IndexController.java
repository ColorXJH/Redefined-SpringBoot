package com.example.springboot2thymeleaf.controller;

import com.example.springboot2thymeleaf.bean.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/27 13:57
 */
@Controller
public class IndexController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
        if("Color".equals(user.getUserName())&&"123456".equals(user.getPassword())){
            //登录成功的用户存储起来
            session.setAttribute("loginUser",user);
            //登录成功重定向到html，防止页面刷新时表单重复提交
            return "redirect:/main.html";
        }
        //重新回到登录页并附带消息
        model.addAttribute("msg","账号密码错误");
        return "login";
    }
    //template下默认是无法访问的，需要经过controller处理，默认只能访问/static 文件夹以及springboot自定义的那些文件夹
    @GetMapping("/main.html")
    public String mainPage(Model model){
        //是否登录成功，拦截器，过滤器，这里偷懒用HttpSession

        //登录进来之后统计url访问次数防止到首页
        ValueOperations<String, String> options = stringRedisTemplate.opsForValue();
        String main=options.get("/main.html");
        String sql=options.get("/sql");
        model.addAttribute("main",main);
        model.addAttribute("sql",sql);
        return "main";
    }
    @GetMapping("/log_out")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }

    @GetMapping("/sql")
    @ResponseBody
    public String sql(){
        return "xixi";
    }
}
