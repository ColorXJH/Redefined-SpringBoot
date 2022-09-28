package com.example.springboot2thymeleaf.bean;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 16:35
 */
@Data
public class Form {
    String email;
    String username;
    MultipartFile headerImg;
    MultipartFile[] photos;
}
