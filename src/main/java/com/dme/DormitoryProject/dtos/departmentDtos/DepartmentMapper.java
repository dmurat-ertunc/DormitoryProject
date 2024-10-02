package com.dme.DormitoryProject.dtos.departmentDtos;

import com.dme.DormitoryProject.entity.Department;

public class DepartmentMapper {
    public static DepartmentDTO toDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setName(department.getName());
        departmentDTO.setId(department.getId());
        return  departmentDTO;
    }
    public static Department toEntity(DepartmentDTO departmentDTO){
        Department department = new Department();

        department.setName(departmentDTO.getName());

        return  department;
    }
}
