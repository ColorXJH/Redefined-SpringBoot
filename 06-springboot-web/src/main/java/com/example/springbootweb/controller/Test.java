package com.example.springbootweb.controller;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: Test
 * @Package: com.example.springbootweb.controller
 * @Description:
 * @Datetime: 2022/1/13 20:11
 * @author: ColorXJH
 */
public class Test {
    public static void main(String[] args) {
            AtomicInteger integer=new AtomicInteger(10);
            for(int i = 0; i < 200; i++) {
                Random random = new Random();
                new Thread(() -> {
                    String name = Thread.currentThread().getName();
                    if(integer.get()<=0) {
                        System.out.println(name + " second kill end");
                        return;
                    }
                    System.out.println(name + " remain " + integer.get());
                    int num = (int)(random.nextInt(3) + 1);
                    int stock = integer.addAndGet(-num);
                    System.out.println(name + " buy " + num + ", stock " + stock);
                    if(stock < 0) {
                        integer.addAndGet(num);
                        System.out.println(name + " not enough stock");
                    } else {
                        System.out.println(name + " buy " + num + " success");
                    }
                }).start();
            }
    }
}
