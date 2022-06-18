package com.example.springbootcache.bean;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/7 10:12
 */
public class Employee {
    private Integer id;
    private String lastName;
    private Integer gender;
    private String email;
    private Integer dId;

    public Employee(Integer id, String lastName, Integer gender, String email, Integer dId) {
        super();
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dId = dId;
    }

    public Employee() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dId=" + dId +
                '}';
    }
}
