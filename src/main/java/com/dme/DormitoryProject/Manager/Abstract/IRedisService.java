package com.dme.DormitoryProject.Manager.Abstract;


import org.springframework.data.redis.core.RedisTemplate;

public interface IRedisService {
    void setData();
    long getData();
}
