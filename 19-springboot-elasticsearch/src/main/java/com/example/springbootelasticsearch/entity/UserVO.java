package com.example.springbootelasticsearch.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: UserVO
 * @Package: com.example.springbootelasticsearch.entity
 * @Description:
 * @Datetime: 2022/9/6 21:09
 * @author: ColorXJH
 */
@Data
public class UserVO {
    private Long id;
    private Integer age;
    private String userName;
    private Date createTime;
    private Date updateTime;
    private String email;
    private Integer version;
    private Double height;

}
