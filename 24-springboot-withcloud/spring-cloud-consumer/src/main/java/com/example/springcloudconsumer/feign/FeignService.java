package com.example.springcloudconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/16 10:04
 */
@FeignClient(name="PROVIDER")
public interface FeignService {
    @GetMapping("/hello/{name}")
    String testSayHello(@PathVariable("name")String name);
}
