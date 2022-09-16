package com.example.springbootactuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/16 13:03
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        //自定义的检查方阿飞

        //return Health.up().build();//健康
        return Health.down().withDetail("msg","服务异常").build();
    }
}
