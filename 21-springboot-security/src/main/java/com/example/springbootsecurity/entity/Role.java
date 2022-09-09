package com.example.springbootsecurity.entity;

import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/9 10:33
 */
@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
}
