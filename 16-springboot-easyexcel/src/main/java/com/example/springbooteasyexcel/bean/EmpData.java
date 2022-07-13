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
    @ExcelProperty("ID")
    private String id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private int age;
    @ExcelProperty("创建时间")
    private Date createTime;
    @ExcelProperty("地址")
    private String addr;
    @ExcelProperty("标志")
    private String flag;
    @ExcelProperty("价格")
    private BigDecimal price;
}
