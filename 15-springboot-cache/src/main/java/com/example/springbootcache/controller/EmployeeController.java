package com.example.springbootcache.controller;

import com.example.springbootcache.bean.Employee;
import com.example.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    /*解决跨域请求
    * 1:@CrossOrigin 注解，在类或者方法上添加上该注解，就可解决。注解上也可加条件来限制请求网页。
    * 2:重写WebMvcConfigurer接口的addCorsMappings方法:详见redefine-springboot-15
    * 3:定义一个过滤器
    * */
    //@CrossOrigin
    public Employee getEmp(@PathVariable("id") Integer id){
    return service.getEmp(id);
    }


    @RequestMapping(value = "/jsonp",method=RequestMethod.GET)
    public String jsonp(HttpServletRequest request) {
        String callback =request.getParameter("callback");
        return callback+"({result:'jsonp'})";
    }
}
