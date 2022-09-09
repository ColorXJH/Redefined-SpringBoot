package com.example.springbootsecurity.dao;

import com.example.springbootsecurity.entity.Permission;
import com.example.springbootsecurity.entity.Role;
import com.example.springbootsecurity.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/9 9:14
 */
public interface QueryDao {

    User queryByUserName(@Param("username") String userName);

    List<Role> queryRolesByUserName(@Param("username") String userName);

    List<Permission>queryPermissionsByUserName(@Param("username") String userName);


}
