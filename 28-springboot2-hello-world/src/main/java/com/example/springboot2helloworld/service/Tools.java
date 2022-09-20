package com.example.springboot2helloworld.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/20 9:18
 */
public class Tools {

    public static void  main(String[] args) throws IOException {
        Properties properties=new Properties();
        properties.load(new FileInputStream("F:\\BaiduNetdiskDownload\\ityouknow\\Redefined-SpringBoot\\28-springboot2-hello-world\\src\\main\\resources\\application.properties"));
        Enumeration enumeration=properties.propertyNames();
        while (enumeration.hasMoreElements()){
            String key=(String)enumeration.nextElement();
            String value=properties.getProperty(key);
            System.out.println("key is "+key+" and value is "+value);
        }
    }
}
