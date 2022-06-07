package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.bean.Employee;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/7 13:13
 */
//@Mapper 或者@MapperScan，将接口扫描装配到容器中
public interface EmployeeMapper {


    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
