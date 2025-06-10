package com.greg.stats_service.domain.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveRedis(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public List<Object> findRedis(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
