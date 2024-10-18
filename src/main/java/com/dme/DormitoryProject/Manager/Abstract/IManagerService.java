package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.response.Result;

import java.util.Optional;

public interface IManagerService {
    Result getAll();
    Result getById(Long id);
    Result saveManager(ManagerDTO managerDTO);
    Result updateManager(Long id,ManagerDTO managerDTO);
    Result deleteManager(Long id);
    Result findBySalaryGreaterThan(int salary);
}
