package com.example.consumer.controller;

import com.example.consumer.feign.ProviderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/13 11:41
 */
@RestController
public class TestController {
    @Autowired
    private ProviderFeignService providerFeignService;
    @Autowired
    private LoadBalancerClient originalClient;

    //使用openfeign
    @GetMapping("/invokeSayHello")
    public String getProviderSayHello(){
        return providerFeignService.sayHello();
    }
    @GetMapping("/invokeServiceUrl")
    public String getProviderServiceUrl(){
        return providerFeignService.getServiceUrl();
    }

    //使用抽象api
    @GetMapping("/another_test1")
    public String test01(){
        ServiceInstance instance = originalClient.choose("zookeeper-provider");
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject(instance.getUri().toString()+"/hello",String.class);
    }

}
