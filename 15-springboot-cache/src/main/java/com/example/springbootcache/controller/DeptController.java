package com.example.springbootcache.controller;

import com.example.springbootcache.bean.Department;
import com.example.springbootcache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/1 10:27
 */
@RestController
public class DeptController {
    @Autowired
    public DeptService deptService;
    @GetMapping("/dept/{id}")
    public Department findById(@PathVariable("id")Integer id){
      return deptService.findById(id);
    }
}
