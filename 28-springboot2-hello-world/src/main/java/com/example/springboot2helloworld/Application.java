package com.example.springboot2helloworld;

import com.example.springboot2helloworld.bean.Pet;
import com.example.springboot2helloworld.bean.Pig;
import com.example.springboot2helloworld.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        //从容器中获取组件
        User user=run.getBean("user01", User.class);
        Pet pet=run.getBean("cat01",Pet.class);
        User user1=run.getBean("user1", User.class);
        Pet pet1=run.getBean("tomcatPet",Pet.class);
        Pig pig=run.getBean(Pig.class);
        System.out.println(user);
        System.out.println(pet);
        System.out.println(user1);
        System.out.println(pet1);
        System.out.println(pig);
    }

}
