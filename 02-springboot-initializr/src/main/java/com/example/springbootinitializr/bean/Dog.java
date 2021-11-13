package com.example.springbootinitializr.bean;

/**
 * @Description:
 * @Author: ColorXJH
 * @CreateDate: 2021/11/13 21:58
 * @Version: 1.0
 */
public class Dog {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //alt+insert 快捷方式
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
