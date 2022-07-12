package com.example.springbooteasyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: EmpData
 * @Package: com.example.springbooteasyexcel.bean
 * @Description:
 * @Datetime: 2022/7/13 0:17
 * @author: ColorXJH
 */
@Data
@EqualsAndHashCode
public class EmpData {
    private String id;
    private String name;
    private int age;
    private Date createTime;
    private String addr;
    private String flag;
    private BigDecimal price;
}
