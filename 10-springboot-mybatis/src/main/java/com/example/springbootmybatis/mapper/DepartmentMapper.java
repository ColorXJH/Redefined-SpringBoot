package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 注解版的增删改查
 * @date 2022/6/7 10:18
 */

//指定这是一个操作数据库的mapper接口，自动装配识别mapper，后面越来越多的时候，每个接口写mapper
//很麻烦，就可以在springboot的主类上（或者mybatis的配置类上）写上注解扫描，这里就不用再写注解了
//@Mapper
public interface DepartmentMapper {
    //根据驼峰命名法可以对应sql字段和属性，如果sql字段为 department_name，那么就和属性departmentName
    //对不上了，在返回时就不能映射到该对象上
    //解决办法：可以自己注册一个bean，配置改属性，也可以在yml文件中配置属性
    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")//返回自增主键并封装到对象中去
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
