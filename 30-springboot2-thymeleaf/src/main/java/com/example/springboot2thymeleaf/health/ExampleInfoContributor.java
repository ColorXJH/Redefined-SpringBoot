package com.example.springboot2thymeleaf.health;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author ColorXJH
 * @version 1.0
 * @description: 也可以自定义配置中设置
 * @date 2022/10/14 14:40
 */
@Component
public class ExampleInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("MyName","Color6").withDetail("example", Collections.singletonMap("Desc","This is my project"));
    }
}
