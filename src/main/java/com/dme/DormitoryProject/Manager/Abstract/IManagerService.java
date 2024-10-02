package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.managerDtos.ManagerDTO;
import com.dme.DormitoryProject.entity.Manager;

import java.util.List;
import java.util.Optional;

public interface IManagerService {
    List<ManagerDTO> getAll();
    Optional<ManagerDTO> getById(Long id);
    Manager saveManager(ManagerDTO managerDTO);
    Manager updateManager(Long id,ManagerDTO managerDTO);
    Manager deleteManager(Long id);
}
