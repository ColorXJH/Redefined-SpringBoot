package com.example.springbootstarterautoconfigurer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/13 13:37
 */
@Component//1:添加注解@Component，声明将这个组件添加至容器中，这样才可以被使用
//Spring boot 1.5以上去除了location属性，可采用@Component的方式注册为组件，然后使用@PropertySource来指定自定义的资源目录。
@ConfigurationProperties(prefix = "color.hello")
public class HelloProperties{

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
