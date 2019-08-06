package com.zqx.java.springboot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
/**
 * springboot的配置读取方式用如下两个注解。
 * 但是不如直接使用spring的@Value好用。
 */
//@PropertySource("classpath:application-custom.properties")
//@ConfigurationProperties(prefix = "core")
public class BaseProperty {

    @Value("${core.ip}")
    private String ip;
    @Value("${core.port}")
    private String port;
    @Value("${core.name}")
    private String name;

    @Override
    public String toString() {
        return "BaseProperty{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

