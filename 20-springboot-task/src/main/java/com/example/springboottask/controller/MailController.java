package com.example.springboottask.controller;

import com.example.springboottask.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 16:12
 */
@RestController
public class MailController {
    @Autowired
    private MailService service;
    @GetMapping("/test1")
    public String tets1(){
        service.sendSimpleMail();
        return "success";
    }
    @GetMapping("/test2")
    public String tets2(){
        service.sendHTMLMail();
        return "success";
    }
    @GetMapping("/test3")
    public String tets3(){
        service.sendMineMail();
        return "success";
    }
    @GetMapping("/test4")
    public String tets4(){
        service.sendStaticResourceMail();
        return "success";
    }
}
