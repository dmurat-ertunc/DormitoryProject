package com.dme.DormitoryProject.Manager.Abstract;


import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import org.springframework.data.redis.core.RedisTemplate;

public interface IRedisService {
    void setData();
    long getData();
    StudentDTO waitStudentData(StudentDTO studentDTO);
}
