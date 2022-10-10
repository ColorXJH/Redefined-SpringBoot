package com.example.springboot2thymeleaf.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot2thymeleaf.bean.MPUser;
import com.example.springboot2thymeleaf.mapper.UserMapper;

import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: TableService继承了MP的常见业务方法，ServiceImpl<UserMapper,MPUser>是MP帮我们实现该接口的类
 * 这样我们基本上不用写业务逻辑，只需要自定义一部分独特的业务逻辑
 * @date 2022/10/10 16:37
 */
public class TableServiceImpl  extends ServiceImpl<UserMapper,MPUser> implements  TableService{
    //ctrl+o 调出查看方法 ==》ctrl+f3查看父类方法
    @Override
    public List<MPUser> queryMyUser() {
        return null;
    }
}
