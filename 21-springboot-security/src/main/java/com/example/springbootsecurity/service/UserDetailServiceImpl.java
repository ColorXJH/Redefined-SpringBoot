package com.example.springbootsecurity.service;

import com.example.springbootsecurity.dao.QueryDao;
import com.example.springbootsecurity.entity.Permission;
import com.example.springbootsecurity.entity.Role;
import com.example.springbootsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/9 8:46
 */
//注意之厄里不要写@Service，不然在配置类中注入会有问题
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    QueryDao queryDao;

    @Override
    public UserDetails loadUserByUsername(String username){
        User user=queryDao.queryByUserName(username);
        if(user!=null){
            List< GrantedAuthority>authorityList=new ArrayList<>();
            List<Role>roleList=queryDao.queryRolesByUserName(username);
            List<Permission> permissionList=queryDao.queryPermissionsByUserName(username);
            //添加角色
            //放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
            String ROLE_PREFIX = "ROLE_";
            roleList.stream().forEach(s->{
                authorityList.add(new SimpleGrantedAuthority(ROLE_PREFIX+s.getRoleName()));
            });
            //添加权限
            permissionList.stream().forEach(s->{
                authorityList.add(new SimpleGrantedAuthority(s.getPermissionName()));
            });
            System.out.printf("当前用户\\s权限为\\s",username,Arrays.toString(authorityList.toArray()));
            user.setAuthorities(authorityList);
            return user;
        }else{
            return null;
        }

    }
}
