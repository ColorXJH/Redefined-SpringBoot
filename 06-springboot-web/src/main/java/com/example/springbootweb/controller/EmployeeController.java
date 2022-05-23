package com.example.springbootweb.controller;

import com.example.springbootweb.dao.DepartmentDao;
import com.example.springbootweb.dao.EmployeeDao;
import com.example.springbootweb.entities.Department;
import com.example.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        System.out.println("修改的员工对象"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee=employeeDao.get(id);
        model.addAttribute("emp",employee);
        //页面要显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);

        //回到修改页面(add是修改，添加二合一页面)
        return "emp/add";
    }


    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
