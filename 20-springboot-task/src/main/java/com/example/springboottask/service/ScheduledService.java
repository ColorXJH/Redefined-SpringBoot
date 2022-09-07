package com.example.springboottask.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 14:34
 */
@Service
public class ScheduledService {
    /* second, minute, hour, day of month, month, week */
    //周一到周五 每一分钟执行一次
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello(){
        System.out.println("---定时执行ScheduledService中的hello---");
        System.out.println(new SimpleDateFormat("yyyy-mm-dd:hh:mm:ss").format(new Date()));
    }
    //"0 0/5 14,18 * * ?" 每天14点和18点整，每隔5分钟执行一次
    //"0 15 10 ? * 1-6" 每月周一到周六10:15执行一次
    //"0 0 2 ? * 6L" 每个月最后一个周六凌晨2点执行一次
    //"0 0 2 LW * ?" 每月最后一个工作日凌晨2点执行一次
    //"0 0 2-4 ? * 1#1" 每月第一个周一凌晨两点到四点期间，每个整点都执行一次
}
