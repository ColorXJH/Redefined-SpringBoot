package com.example.springboot2thymeleaf.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:自定义健康检查监控端点
 * @date 2022/10/14 14:20
 */
@Component
public class MyCommonHealthIndicator extends AbstractHealthIndicator {
    /**
     * Description: 真实的检查方法
     * @Author: ColorXJH
     * @Date: 2022/10/14 14:22
     * @param builder
     * @Return: void
     **/
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        Map<String,Object> map=new HashMap<>();
        if(true){
            //builder.up();//健康的
            builder.status(Status.UP);
            map.put("count",1);
            map.put("name","ColorXJH");
        }else{
            //builder.down();//不健康的
            builder.status(Status.OUT_OF_SERVICE);
            map.put("msg","出现问题了");
        }
        //携带信息
        builder.withDetails(map).withDetail("author","MASTER");
    }
}
