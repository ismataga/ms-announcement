package com.example.authentication.cache;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CacheUtil<T> {

    private final RedisTemplate<String, T> redisTemplate;

    public T getBucket(String cacheKey) {
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate.opsForValue().get(cacheKey);
    }

    public void saveToCache(String key, T data, Long expireTime, TimeUnit timeUnit) {
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.opsForValue().set(key, data, expireTime, timeUnit);
    }
}