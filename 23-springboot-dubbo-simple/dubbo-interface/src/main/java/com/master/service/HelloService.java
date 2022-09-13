package com.master.service;

import java.util.concurrent.CompletableFuture;

/**
 * @ClassName: HelloService
 * @Package: com.master.service
 * @Description:
 * @Datetime: 2022/9/13 19:14
 * @author: ColorXJH
 */
public interface HelloService {
    String sayHello(String name);
    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
