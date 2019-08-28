package com.zqx.java.springboot.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP代理：面向切面
 * 开启注解的两种方式：
 *  1)@Component
 *  2)在启动入口类SpringbootApplication中配置@EnableAspectJAutoProxy和@Bean生成DefaultAspectJProxyFactory对象
 *
 * 关于@Pointcut("execution(* com.zqx.java.springboot.serviceImpl.*.*(..))")规则的问题
 */

@Aspect
//@Component
public class DefaultAspectJProxyFactory {


    @Pointcut("execution(* com.zqx.java.springboot.serviceImpl.*.*(..))")
    public void defaultAop(){}

    @Before("defaultAop()")
    public void doBeforeAction(JoinPoint joinPoint) {
        System.out.println("doBeforeAction...");
    }

    @After("defaultAop()")
    public void doAfterAction(JoinPoint joinPoint) {
        System.out.println("doAfterAction");
    }

}
