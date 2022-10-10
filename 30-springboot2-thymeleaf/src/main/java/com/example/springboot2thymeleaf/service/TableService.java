package com.example.springboot2thymeleaf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot2thymeleaf.bean.MPUser;

import java.util.List;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/10 16:35
 */
public interface TableService extends IService<MPUser> {
    List<MPUser> queryMyUser();
}
