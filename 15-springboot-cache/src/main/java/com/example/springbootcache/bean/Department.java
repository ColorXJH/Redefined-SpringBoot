package com.example.springbootcache.bean;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/7 10:14
 */
public class Department {
    private Integer id;
    private String departmentName;

    public Department(Integer id, String departmentName) {
        super();
        this.id = id;
        this.departmentName = departmentName;
    }

    public Department() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
