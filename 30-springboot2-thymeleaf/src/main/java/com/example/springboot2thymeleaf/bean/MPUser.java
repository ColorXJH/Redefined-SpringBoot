package com.example.springboot2thymeleaf.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: MP的user类
 * @date 2022/10/10 15:29
 */
@Data
@TableName("user")
public class MPUser {
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String password;
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
