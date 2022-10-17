package com.example.springboot2profile.entity;

import com.example.springboot2profile.inf.PersonInf;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 14:13
 */
@Profile("prod")
@Data
@Component
@ConfigurationProperties(prefix = "persons")
public class P1Inf implements PersonInf {
    private String name;
    private Integer age;
}
