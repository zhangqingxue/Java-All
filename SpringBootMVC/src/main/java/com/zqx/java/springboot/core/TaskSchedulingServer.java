package com.zqx.java.springboot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedulingServer {

    @Value("${scheduled.executeIfDefault}")
    boolean executeIfDefault;

    @Async
    @Scheduled(cron = "${scheduled.executeCron}")
    public void execute() {
        if (executeIfDefault) {
            System.out.println("=====执行开始======");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("<<<<<<<<<<<执行结束>>>>>>>>>>");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
