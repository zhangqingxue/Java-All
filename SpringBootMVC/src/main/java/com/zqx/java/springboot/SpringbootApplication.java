package com.zqx.java.springboot;

import com.zqx.java.springboot.core.DefaultAspectJProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync  // 异步注解
@EnableScheduling  // 定时任务注解
@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringbootApplication {

    @Bean
    public DefaultAspectJProxyFactory createPorxy(){
        return new DefaultAspectJProxyFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
