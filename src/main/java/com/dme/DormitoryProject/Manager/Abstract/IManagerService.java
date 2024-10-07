package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.entity.Manager;
import com.dme.DormitoryProject.response.MyResponseEntity;
import com.dme.DormitoryProject.response.Result;

import java.util.List;
import java.util.Optional;

public interface IManagerService {
    Result getAll();
    Optional<ManagerDTO> getById(Long id);
    Manager saveManager(ManagerDTO managerDTO);
    Manager updateManager(Long id,ManagerDTO managerDTO);
    Manager deleteManager(Long id);
}
