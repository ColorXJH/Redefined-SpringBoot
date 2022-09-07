package com.example.springboottask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync//开启异步注解
@EnableScheduling//开启定时任务
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

//1:异步任务：
    //@EnableAsync+@Async
//2:定时任务：spring提供了异步任务调度的方式：
    //TaskExecutor与 TaskScheduler接口
    //两个注解：@EnableScheduling, @Scheduled
        //cron 表达式
            //          秒           分           小时              日期              月份          星期
    //允许值：//        0-59         0-59         0-23              1-31             1-12          0-7/SUM-SAT  0,7是SUM
//允许的特殊字符       , - * /       , - * /      , - * /           , - * ? L W C    , - * /       , - * ? / L C #

//特殊字符代表含义
    //1: ,     枚举
    //2: -     区间
    //3: *     任意
    //4: /     步长
    //5: ?     日/星期冲突匹配
    //6: L     最后
    //7: W     工作日
    //8: C     和Calender联系后计算过的值
    //9: #     星期 4#2  第二个星期三

//3:邮件任务
    //邮件任务需要引入starter:=>spring-boot-starter-mail
    //springboot自动配置MailSenderAutoConfiguration
    //定义MailProperties内容，配置在application.yml中
    //自动装配JavaMailSender
    //测试邮件发送