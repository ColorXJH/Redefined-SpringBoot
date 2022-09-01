package com.example.springbootcache.mapper;

import com.example.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Select;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/16 9:03
 */
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    Department findById(Integer id);
}
