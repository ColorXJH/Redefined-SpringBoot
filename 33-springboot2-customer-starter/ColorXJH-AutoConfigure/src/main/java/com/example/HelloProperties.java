package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/10/17 16:25
 */
@ConfigurationProperties("color")
public class HelloProperties {
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
