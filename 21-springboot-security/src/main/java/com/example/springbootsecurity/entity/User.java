package com.example.springbootsecurity.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 自定义用户实体类
 * @date 2022/9/9 8:27
 */
@Data
public class User implements UserDetails {
    private Integer id;
    private String userName;
    private String passWord;
    private boolean noLockedFlag=true;
    private boolean noExpiredFlag=true;
    private boolean credentialNoExpired=true;
    private boolean enabled=true;
    //security存储权限认证用的
    Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return noExpiredFlag;
    }

    @Override
    public boolean isAccountNonLocked() {
        return noLockedFlag;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNoExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
