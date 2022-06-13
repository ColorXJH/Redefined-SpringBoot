package com.example.springbootstarterautoconfigurer;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/6/13 13:36
 */
public class HelloService {

    public HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name){
        return helloProperties.getPrefix()+"-"+name+helloProperties.getSuffix();
    }
}
