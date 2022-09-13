package com.example.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 调用服务提供者的客户端，name为注册中心name,为对应前缀
 * @date 2022/9/13 11:46
 */
@FeignClient(name="zookeeper-provider",path = "/HelloController")
public interface ProviderFeignService {
     @GetMapping("/hello")
     String sayHello();
     @GetMapping("/serviceUrl")
     String getServiceUrl();

}
