package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.response.Result;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    Result getAll();
    Result getById(Long id);
    Result saveDepartment(DepartmentDTO departmentDTO);
    Result updateDepartment(Long id, DepartmentDTO departmentDTO);
    Result deleteDepartment(Long id);
    Result startingWithWord(String prefix);
}
