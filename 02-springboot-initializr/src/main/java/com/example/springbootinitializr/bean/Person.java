package com.example.springbootinitializr.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ColorXJH
 * @CreateDate: 2021/11/13 21:55
 * @Version: 1.0
 */
//将配置文件中配置的每一个值都映射到这个组建中,@ConfigurationProperties告诉springboot将本类中的所有属性和配置文件中相关的配置进行绑定
    //prefix="person" 配置文件中那个下面的所有属性进行一一映射，使用时该类必须要被加载到容器中
@ConfigurationProperties(prefix = "person")
@Component  //以上二：方法1
//@Component  //或者 //@Configuration
//@EnableConfigurationProperties(Person.class)
//@ConfigurationProperties(prefix = "person")//以上三：方拾2



public class Person {
    private String name;
    private int age;
    private boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

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

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String  toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
