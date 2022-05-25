package com.example.springbootweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @ClassName: MyListener
 * @Package: com.example.springbootweb.listener
 * @Description:
 * @Datetime: 2022/5/25 15:43
 * @author: ColorXJH
 */
public class MyListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized-----web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed-----web应用销毁");
    }
}
