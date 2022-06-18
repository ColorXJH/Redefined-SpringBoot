package com.example.springbootcache.service;

import com.example.springbootcache.bean.Employee;
import com.example.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: EmployeeService
 * @Package: com.example.springbootmybatis.service
 * @Description:
 * @Datetime: 2022/6/18 17:02
 * @author: ColorXJH
 */
@Service
public class EmployeeService {
    //@autowired和@resource注解的区别是什么？
    //区别：1、@Autowired注解由Spring提供，只按照byType注入；@resource注解由J2EE提供，默认按照byName自动注入。
    //2、@Autowired默认按类型进行装配，@Resource默认按照名称进行装配。

    //1、@Autowired
    //由Spring提供，只按照byType注入

    //2、@Resource
    //由J2EE提供，默认按照byName自动注入
    //@Resource有两个重要的属性：name和type
    //Spring将@Resource注解的name属性解析为bean的名字，type属性则解析为bean的类型。
    //所以如果使用name属性，则使用byName的自动注入策略，而使用type属性则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。



    @Resource
    //这里如果使用@Autowired会报错没有识别到类，@Resourece则不会，因为她表示资源不是表示组件
    EmployeeMapper employeeMapper;

    public Employee getEmp(Integer id){
        System.out.println("查询-----------");
        Employee employeeById = employeeMapper.getEmployeeById(id);
        return employeeById;
    }
}
