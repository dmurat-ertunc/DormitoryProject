package com.dme.DormitoryProject.Manager.Abstract;


import com.dme.DormitoryProject.dtos.studentDtos.StudentDTO;
import org.springframework.data.redis.core.RedisTemplate;

public interface IRedisService {
    void setData(Long id);
    long getData(Long id);
    void waitStudentData(StudentDTO studentDTO);
    StudentDTO getStudentData();
}
