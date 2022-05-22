package com.example.springbootweb.controller;

import com.example.springbootweb.dao.DepartmentDao;
import com.example.springbootweb.dao.EmployeeDao;
import com.example.springbootweb.entities.Department;
import com.example.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Autowired
    DepartmentDao departmentDao;
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
    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息"+employee);
        employeeDao.save(employee);
        //来到员工列表页面
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }
}
