package com.alex.config;

import com.alex.service.RedisService;
import com.alex.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;


public class RedisBaseConfig implements CachingConfigurer {

    @Bean
    @SuppressWarnings("all")
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(factory);
        RedisSerializationContext.SerializationPair pair =
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new Jackson2JsonRedisSerializer(Object.class));
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        return new RedisCacheManager(writer, config);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashKeySerializer(valueSerializer());
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean
    public RedisService redisService(){
        return new RedisServiceImpl();
    }

}
