package com.dme.DormitoryProject.Manager.Concrete;

import com.dme.DormitoryProject.Manager.Abstract.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class RedisManager implements IRedisService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setData() {
        String key = "kod";
        Random random = new Random();
        int value = 100000 + random.nextInt(900000);
        redisTemplate.opsForValue().set(key, value,120,TimeUnit.SECONDS);
    }

    @Override
    public long getData() {
        String key = "kod";
        return (Long) redisTemplate.opsForValue().get(key);
    }
}
