package com.example.springboot2thymeleaf.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/13 16:05
 */
@Component
public class SpringAsyncMeth {
    @Async
    public void spAsync(){
        longtimeMethod();
    }

    @Async
    public Future spAsyncRet(){
        String result=longtimeMethod2();
        return new AsyncResult(result);
    }

    public void longtimeMethod(){
        try {
            Thread.sleep(3000);
            System.out.println("睡着了333");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String  longtimeMethod2(){
        try {
            Thread.sleep(3000);
            System.out.println("睡着了444");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "睡着了444";
    }
}
