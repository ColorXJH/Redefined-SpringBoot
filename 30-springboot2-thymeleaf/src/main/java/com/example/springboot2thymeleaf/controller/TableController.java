package com.example.springboot2thymeleaf.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot2thymeleaf.bean.MPUser;
import com.example.springboot2thymeleaf.mapper.UserMapper;
import com.example.springboot2thymeleaf.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/28 11:11
 */
@Controller
public class TableController {
    @Autowired//报红不影响
    JdbcTemplate jdbcTemplate;

    @Autowired
    TableService tableService;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/basic_table")
    public String basicTable(Integer a){
        int x=10/a;
        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        List<MPUser>list=tableService.list();
        //分页查询数据
        Page<MPUser>userPage=new Page<>(pn,3);
        //分页查询结果
        Page<MPUser> page = tableService.page(userPage, null);
        model.addAttribute("users",page);
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
    @GetMapping("/user/delete/{id}")
    public String userDelete(@PathVariable("id")Integer id, @RequestParam("pn")Integer pn, RedirectAttributes ra){
        tableService.removeById(id);
        //方法1：
        //return "redirect:/dynamic_table?pn="+pn;
        //方法2：,添加RedirectAttributes
        ra.addAttribute("pn",pn);//参数以url的方式添加进去
        return "redirect:/dynamic_table";
    }

}
