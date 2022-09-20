package com.example.springboot2helloworld.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: yaml配置绑定类
 * @date 2022/9/20 14:46
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {
    private Date dtest;
    private Boolean dboolean;
    private String dstring;
    private Integer dnumber;
    private String dnull;
    private List<String> dlist;
    private String[] interest;
    private List<String> dlist2;
    private Map<String,String> dmap;
    private Map<String,String> dmap2;
    private MyCar car;
    private Set<Double> salary;
    private Map<String,List<MyCar>>allCars;



}
