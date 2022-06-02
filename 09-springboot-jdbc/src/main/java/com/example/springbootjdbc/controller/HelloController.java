package com.example.springbootjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/2 15:01
 */
@Controller
public class HelloController {

    @Autowired
    JdbcTemplate template;

    @ResponseBody
    @GetMapping("/query")
    public Map<String ,Object> map(){
        List<Map<String, Object>> maps = template.queryForList("select * from mybook");
        return  maps.get(0);
    }

}
