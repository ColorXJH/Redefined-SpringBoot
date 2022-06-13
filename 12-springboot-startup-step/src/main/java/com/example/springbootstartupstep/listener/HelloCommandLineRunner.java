package com.example.springbootstartupstep.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 测试springboot的启动方法
 * @date 2022/6/13 9:21
 */

//ApplicationRunner和CommandLineRunner是在springboot容器中加载的，其他两个是在类路径META-INF文件夹下的文件中配置出来的
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner...run..."+ Arrays.asList(args));
    }
}
