package com.example.springbootweb.controller;

import com.example.springbootweb.dao.EmployeeDao;
import com.example.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * @ClassName: EmployeeController
 * @Package: com.example.springbootweb.controller
 * @Description:
 * @Datetime: 2022/5/20 18:09
 * @author: ColorXJH
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    /**
     * 功能描述:查询返回所有员工列表
     * @param:
     * @Return: java.lang.String
     * @Author: ColorXJH
     * @Date: 2022/5/20 18:09
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();//ctrl+1实现代码自动补全
        //将请求域中放入model map  modelmap都可以带入页面
        model.addAttribute("emps",all);
        return "emp/list";
    }
}
