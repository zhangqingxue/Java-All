package com.zqx.java.springboot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedulingServer {

    @Value("${scheduled.executeIfDefault}")
    boolean executeIfDefault;

    /**
     * {@link org.springframework.scheduling.annotation.Async} 指定自定义MyAsyncConfigurer类中指定的executor线程池
     * 也可以通过value属性指定特定线程池
     */
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
