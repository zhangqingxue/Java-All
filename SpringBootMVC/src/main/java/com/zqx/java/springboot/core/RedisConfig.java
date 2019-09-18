package com.zqx.java.springboot.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    /**
     * RedisTemplate配置
     */
    @Bean
    @Primary
    public StringRedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisTemplate redisTemplate = new StringRedisTemplate(lettuceConnectionFactory);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


}
