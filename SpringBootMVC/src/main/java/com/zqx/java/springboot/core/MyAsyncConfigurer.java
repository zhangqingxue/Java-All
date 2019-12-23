package com.zqx.java.springboot.core;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MyAsyncConfigurer implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        /* 此处要使用new ThreadPoolExecutor()方式创建线程池。目前懒在床上,就先这样吧。*/
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        return executorService;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }

    /**
     * 处理异常。打印异常信息(区别于UncaughtExceptionHandler)
     */
    class MyAsyncUncaughtExceptionHandler implements  AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            System.err.println("执行错误, 错误信息如下：");
            System.err.println("方法名称：" + method.getName() + ", 错误信息：" + ex.getMessage());
        }
    }
}
