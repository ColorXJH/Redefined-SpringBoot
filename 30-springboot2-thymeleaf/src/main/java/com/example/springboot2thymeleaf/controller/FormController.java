package com.example.springboot2thymeleaf.controller;

import com.example.springboot2thymeleaf.bean.Form;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 16:10
 */
@Controller
public class FormController {
    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }
    //文件上传
    @PostMapping("/upload")
    public String upload(Form form) throws IOException {
        MultipartFile[] photos=form.getPhotos();
        MultipartFile image=form.getHeaderImg();
        String filepath="F:\\360\\test";

        if(!image.isEmpty()){
            image.transferTo(new File(filepath+"\\1.jpg"));
        }
        if(photos.length>0){
            for(int i=0){

            }
        }
        return "/main";
    }

}

