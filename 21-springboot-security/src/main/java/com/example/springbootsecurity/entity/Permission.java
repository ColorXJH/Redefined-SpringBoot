package com.example.springbootsecurity.entity;

import lombok.Data;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/9 10:34
 */
@Data
public class Permission {
    private Integer id;
    private String permissionName;
    private String permissionDesc;
}
