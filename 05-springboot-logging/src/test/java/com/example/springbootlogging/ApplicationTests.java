package com.example.springbootlogging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("---开始记录日志---");
        Logger logger = LoggerFactory.getLogger(getClass());//alt+enter:自动提示补全
        //日志的级别：由低到高，可以调整需要输出的日志级别，日志就会在这个日志及其高的级别生效，默认使用info级别（及以上）日志,可以在配置文件中给某个包，类配置指定的日志级别
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }

}
