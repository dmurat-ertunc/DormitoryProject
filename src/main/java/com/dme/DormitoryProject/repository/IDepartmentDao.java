package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.dtos.departmentDtos.DepartmentDTO;
import com.dme.DormitoryProject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepartmentDao extends JpaRepository<Department,Long> {
    //Department findDepartmentById(Long id);
    //Optional<Department> findById(Long id);
    @Query("SELECT d FROM Department d WHERE d.isDeleted = false")
    List<Department> findAll();
    //DepartmentDTO findDepartmenDTOById(Long id);
    List<Department> findByNameStartingWith(String prefix);
}
