package com.example.springbootdatajpa.entity;

import javax.persistence.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: orm 框架 对象 关系 映射框架的 对象实体
 * @date 2022/6/8 9:25
 */

//使用jpa注解配置映射关系
@Entity  //告诉jpa这是一个实体类（和数据表映射的类）
@Table(name="tbl_user")//指定和哪个数据表对应，如果省略默认表名就是类名小写 user
public class User {
    @Id//这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键策略：主键由数据库自动生成（主要是自动增长型）
    private Integer id;
    @Column(name="last_name") //这是和数据表对应的一个列
    private String lastName;
    @Column //省略默认列名就是属性
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
