package com.dme.DormitoryProject.Manager.Concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisManager {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getData(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }
}
