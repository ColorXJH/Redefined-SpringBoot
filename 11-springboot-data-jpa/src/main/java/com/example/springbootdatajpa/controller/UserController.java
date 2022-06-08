package com.example.springbootdatajpa.controller;

import com.example.springbootdatajpa.entity.User;
import com.example.springbootdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/8 10:33
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user=userRepository.findById(id).orElse(new User());
        return user;
    }

    @GetMapping("/user")
    public User insertUser(User user){
        return userRepository.save(user);
    }


}
