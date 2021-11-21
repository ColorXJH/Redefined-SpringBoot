package com.example.springbootinitializr.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
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
@PropertySource(value={"classpath:person.properties"})
//@Validated
//@Component  //或者 //@Configuration
//@EnableConfigurationProperties(Person.class)
//@ConfigurationProperties(prefix = "person")//以上三：方拾2
public class Person {

    //<bean class="Person">
    //  <property name="lastname" value="字面量/${key}从环境变量，配置文件中获取值/#{spEL}"></property>
    //</bean>

    //@Component+@Value也可以实现Component+@ConfigurationProperties(prefix = "person")相似的功能，但是前置支持spel表达式，后支不支持，后者支持松散绑定以及批量获取文件中的属性，但是后置需要一个一个指定
    //同时后置可以实现配合@Validated实现校验功能，但是配合@Value是无效的
    //关于这两者如何使用：如果只是在业务逻辑中需要获取一下配置文件的某项值，使用@Value,如果我们专门编写了一个javabean来和配置文件映射，就直接使用@ConfigurationProperties(prefix = "person")

        //关于@PropertySource与@ImportResource
        //1:@PropertySource:加载指定的配置文件(只适用与读取.properties文件),适用于提取一些公共配置从application.properties中抽取出来
        //2：@ImportResource:导入spring的配置文件，让配置文件里面的内容生效,springboot里面没有spring的配置文件，我们自己编写的配置文件也不能自动识别，想让spring的配置文件生效，加载进来
            //就需要使用这个注解，比如标在Application主配置类上
        //springboot推荐给容器添加组件的方式是：1：配置类（注解方式）===spring配置文件


    //@Value("${person.name}")
    //@Email //name 必须是邮箱格式
    private String name;
    //@Value("#{11*2}")
    private int age;
    //@Value("true")
    private boolean boss;
    private Date birth;
    //@Value("${person.maps}")//不支持复杂类型注解封装
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
