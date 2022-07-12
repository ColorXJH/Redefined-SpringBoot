package com.example.springbooteasyexcel.dao;

import com.example.springbooteasyexcel.bean.EmpData;
import com.example.springbooteasyexcel.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: EmployeeDao
 * @Package: com.example.springbooteasyexcel.dao
 * @Description:
 * @Datetime: 2022/7/12 20:26
 * @author: ColorXJH
 */
@Mapper
public interface EmployeeDao {
    /**
     *  excel插入
     */
    void insertBatch(List<Employee> list);
    List<Employee> queryData();

    public int queryCount();

    public List<Employee>queryDataByPage(@Param("start")int start,@Param("end")int end);
}
