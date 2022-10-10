package com.example.springboot2thymeleaf.controller;

import com.example.springboot2thymeleaf.bean.Form;
import com.example.springboot2thymeleaf.bean.employee;
import com.example.springboot2thymeleaf.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 16:10
 */
@Controller
public class FormController {
    @Autowired
    AccountMapper mapper;

    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }
    //文件上传
    @PostMapping("/upload")
    public String upload(Form form
            //,@RequestPart("images")MultipartFile//也可以使用这个注解表示上传的文件
    ) throws IOException {
        MultipartFile[] photos=form.getPhotos();
        MultipartFile image=form.getHeaderImg();
        String filepath="F:\\360\\test";
        if(!image.isEmpty()){
            image.transferTo(new File(filepath+"\\"+image.getOriginalFilename()));
        }
        if(photos.length>0){
            for(int i=0;i<photos.length;i++){
                photos[i].transferTo(new File(filepath+"\\"+ photos[i].getOriginalFilename()));
            }
        }
        return "redirect:/main.html";
    }
    @GetMapping("/findAll")
    @ResponseBody
    public List<employee> findAll(){
        List<employee>ps= mapper.findAll();
        System.out.println(ps);
        return ps;
    }
    @GetMapping("/findOne")
    @ResponseBody
    public employee findOne(){
        return mapper.findOne();
    }
    @PostMapping("/insertOne")
    @ResponseBody
    public employee insertOne(employee employee){
        mapper.insertOne(employee);
        return employee;
    }
}

