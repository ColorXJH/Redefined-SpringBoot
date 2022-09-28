package com.example.springboot2thymeleaf.controller;

import com.example.springboot2thymeleaf.bean.Person;
import com.example.springboot2thymeleaf.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/basic_table")
    public String basicTable(){
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){
        List<Person> persons=new ArrayList<>();
        persons.add(new Person("1","张三",22,"123.11.com"));
        persons.add(new Person("2","张4",23,"123.12.com"));
        persons.add(new Person("3","张5",24,"123.13.com"));
        persons.add(new Person("4","张6",25,"123.14.com"));
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
}
