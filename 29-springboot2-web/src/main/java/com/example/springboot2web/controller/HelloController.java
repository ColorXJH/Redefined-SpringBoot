package com.example.springboot2web.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/test/{id}/{name}")
    public Object test(@PathVariable("id") String id,
                       @PathVariable("name") String name,
                       @PathVariable Map<String,String> values,
                       @RequestHeader("User-Agent")String agent,
                       @RequestHeader Map<String,String> heades,
                       @RequestParam("car")String car,
                       @RequestParam("price")Integer price,
                       @RequestParam("interest") List<String> interest,
                       @RequestParam Map<String,String> params,
                       @CookieValue("Idea-3defc7d9")String cookieValue,
                       @CookieValue("Idea-3defc7d9")Cookie cookie){
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("values",values);
        map.put("user-agent",agent);
        map.put("headers",heades);
        map.put("car",car);
        map.put("price",price);
        map.put("interest",interest);
        map.put("params",params);
        map.put("cookieValue",cookieValue);
        map.put("cookie",cookie);

        return map;
    }
    /*获取表单请求体信息*/
    @PostMapping("/test2")
    public Map test2(@RequestBody String content){
        Map<String ,Object>map=new HashMap<>();
        map.put("content",content);
        return map;
    }


}
