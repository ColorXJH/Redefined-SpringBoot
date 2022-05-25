package com.example.springbootwebjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: HelloController
 * @Package: com.example.springbootwebjsp.controller
 * @Description:
 * @Datetime: 2022/5/25 22:18
 * @author: ColorXJH
 */
@Controller
public class HelloController {
    @GetMapping("/abc")
    public String hello(Model model){
        //配置文件中配置了路径
        model.addAttribute("msg","你好" );
        return "success";
    }
}
