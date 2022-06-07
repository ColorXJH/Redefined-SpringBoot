package com.example.springbootmybatis.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.springbootmybatis.bean.Department;
import com.example.springbootmybatis.bean.Employee;
import com.example.springbootmybatis.mapper.DepartmentMapper;
import com.example.springbootmybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/7 10:53
 */
@RestController
public class DeptController {
    @Autowired
    DepartmentMapper mapper;
    @Autowired
    EmployeeMapper employeeMapper;


    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return mapper.getDeptById(id);
    }
    @GetMapping("/dept")
    public Department insertDept(Department department){
        mapper.insertDept(department);
        return department;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

    //获取 Druid 的监控数据
    //Druid 的监控数据可以在 开启 StatFilter 后，通过 DruidStatManagerFacade 进行获取;
    @GetMapping("/stat")
    public Object druidStat(){
        //获取数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
