package com.example.springbootcache.controller;

import com.example.springbootcache.bean.Employee;
import com.example.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EmployeeController
 * @Package: com.example.springbootcache.controller
 * @Description:spring缓存搭建
 * @Datetime: 2022/6/18 17:21
 * @author: ColorXJH
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
    return service.getEmp(id);
    }
}
