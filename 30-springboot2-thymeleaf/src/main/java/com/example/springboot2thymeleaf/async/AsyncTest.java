package com.example.springboot2thymeleaf.async;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @description: 关于异步的一些讨论
 * @author ColorXJH
 * @date 2022/10/13 15:53
 * @version 1.0
 */
@RestController
public class AsyncTest {
    //直接new线程
    @GetMapping("/thread")
    public void useThread(){
        Thread t=new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                longTimeService();
            }
        };
        t.start();
        System.out.println("线程的异步方法");
    }

    //使用线程池
    private ExecutorService executorService= Executors.newFixedThreadPool(10);
    @GetMapping("/executor")
    public void useExecutors(){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    longTimeService();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("线程池的异步方法");
    }


    //spring的异步方法 //需要在启动类开启@EnableAsync
    @Autowired
    SpringAsyncMeth springAsyncMeth;
    @GetMapping("/springAsync")
    public void  spAsync(){
        springAsyncMeth.spAsync();
        System.out.println("spring的异步方法");
    }

    //spring 异步方法带返回值
    @GetMapping("/springAsyncReturn")
    public String springAsyncReturn() throws ExecutionException, InterruptedException, TimeoutException {
        Future future=springAsyncMeth.spAsyncRet();
        System.out.println("---spring 异步方法带返回值---");
        String result=(String)future.get(5000, TimeUnit.MILLISECONDS);
        return result;
    }

    //原生Future
    @GetMapping("/futureRet")
    public String futureRet() throws InterruptedException, ExecutionException {
        Future future=longTimeMethod2();
        System.out.println("--原生Future--");
        String result=(String)future.get();
        return "result";
    }

    public void longTimeService() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("睡着了222");
    }

    public String longTimeService2() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("睡着了444");
        return "--睡着了444--";
    }
    private Future longTimeMethod2() {
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //获取异步Future对象
        Future future = threadPool.submit(new Callable() {
            @Override
            public String call() throws InterruptedException {
                return longTimeService2();
            }
        });
        return future;
    }
}
