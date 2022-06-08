package com.example.springbootdatajpa.repository;

import com.example.springbootdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/8 10:15
 */


//泛型传入实体类和主键类型
public interface UserRepository extends JpaRepository<User,Integer> {
}
