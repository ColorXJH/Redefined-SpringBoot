package com.example.springbooteasyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/7 10:12
 */
@Getter
@Setter
@EqualsAndHashCode
public class Employee {
    @ExcelProperty("ID")
    private String id;
    @ExcelProperty("NAME")
    private String name;
    @ExcelProperty("AGE")
    private int age;
    @ExcelProperty("CREATE_TIME")
    private Date createTime;
    @ExcelProperty("ADDR")
    private String addr;
    @ExcelProperty("FLAG")
    private String flag;
    @ExcelProperty("PRICE")
    private BigDecimal price;
}
