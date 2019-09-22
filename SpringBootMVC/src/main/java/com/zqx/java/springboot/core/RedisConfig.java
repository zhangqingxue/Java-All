package com.zqx.java.springboot.core;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.ArrayList;
import java.util.List;

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

    @Bean
    RedisClusterCommands<String, String> redisCommands() {
        List<RedisURI> uriList = new ArrayList<>();
//        nodes.forEach(node -> {
//            String[] addrStr = node.split(":");
//            String host = addrStr[0];
//            int port = Integer.parseInt(addrStr[1]);
//
//            RedisURI redisUri = RedisURI.Builder.redis(host).withPort(port).build();
//            uriList.add(redisUri);
//        });
        RedisClusterClient redisClient = RedisClusterClient.create(uriList);
        StatefulRedisClusterConnection<String, String> connection = redisClient.connect();
        RedisClusterCommands<String, String> syncCommands = connection.sync();

        return syncCommands;
    }


}
