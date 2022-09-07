package com.example.springboottask.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 13:49
 */
@Service
public class AsyncService {

    public void hello(){
        System.out.println("--处理数据中--");
        try {
            Thread.sleep(3000);
            System.out.println("--处理数据结束--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Async//开启异步，创建线程池调用
    public void asyncHello(){
        System.out.println("--处理数据中--");
        try {
            Thread.sleep(3000);
            System.out.println("--处理数据结束--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
