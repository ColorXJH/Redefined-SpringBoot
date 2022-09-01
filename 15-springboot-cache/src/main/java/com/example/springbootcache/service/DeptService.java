package com.example.springbootcache.service;

import com.example.springbootcache.bean.Department;
import com.example.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/1 10:31
 */
@Service
public class DeptService {
    @Autowired
    public DepartmentMapper departmentMapper;
    @Autowired
    CacheManager redisCacheManager;
    //编码的方式使用缓存
    public Department findById(Integer id){
        Cache cache=redisCacheManager.getCache("dept");
        Department department=null;
        if(cache!=null&&cache.get("dept:"+id)!=null){
            department=(Department) cache.get("dept:"+id).get();
        }else{
            department= departmentMapper.findById(id);
            cache.put("dept:"+id,department);
        }
        return  department;



    }
}
