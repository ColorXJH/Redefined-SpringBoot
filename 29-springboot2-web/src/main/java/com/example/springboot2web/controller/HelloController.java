package com.example.springboot2web.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/21 14:49
 */
@RestController
public class HelloController {
    /*restful 风格*/

    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping("/user")
    public String getUser(){
        return "get-user";
    }
    //@RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping("/user")
    public String saveUser(){
        return "save-user";
    }
    //@RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping("/user")
    public String deleteUser(){
        return "delete-user";
    }
    //@RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping("/user")
    public String putUser(){
        return "put-user";
    }

}
