package com.example.springboot2thymeleaf.mapper;

import com.example.springboot2thymeleaf.bean.employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/10 11:41
 */
@Mapper//或者使用MapperScan扫描
public interface AccountMapper {
    List<employee> findAll();
}
