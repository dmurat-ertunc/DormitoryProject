package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<DepartmentDTO> getAll();
    Optional<DepartmentDTO> getById(Long id);
    Department saveDepartment(DepartmentDTO departmentDTO);
    Department updateDepartment(Long id, DepartmentDTO departmentDTO);
    Department deleteDepartment(Long id);
}
