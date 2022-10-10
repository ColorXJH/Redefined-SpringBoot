package com.example.springboot2thymeleaf.controller;

import com.example.springboot2thymeleaf.bean.MPUser;
import com.example.springboot2thymeleaf.bean.Person;
import com.example.springboot2thymeleaf.bean.User;
import com.example.springboot2thymeleaf.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 11:11
 */
@Controller
public class TableController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/basic_table")
    public String basicTable(Integer a){
        int x=10/a;
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){
        List<Person> persons=new ArrayList<>();
        persons.add(new Person("1","张三",22,"123.11.com"));
        persons.add(new Person("2","张4",23,"123.12.com"));
        persons.add(new Person("3","张5",24,"123.13.com"));
        persons.add(new Person("4","张6",25,"123.14.com"));
        /*if(persons.size()>3){
            throw new UserToManyException();
        }*/
        User users=new User();
        users.setRecords(persons);
        users.setPages(2);
        users.setTotal(20);
        users.setCurrent(3);
        model.addAttribute("users",users);
        return "table/dynamic_table";
    }
    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }
    @GetMapping("/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }

    @ResponseBody
    @GetMapping("/queryTable")
    public Object queryTable(){
        return  jdbcTemplate.queryForList("SELECT * FROM test.employee");
    }

    @GetMapping("/test123")
    public void test1(){
        System.out.println(("----- selectAll method test ------"));
        List<MPUser> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
