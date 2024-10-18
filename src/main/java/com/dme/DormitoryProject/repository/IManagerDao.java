package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IManagerDao extends JpaRepository<Manager,Long> {
    //Manager findManagerById(Long id);
    @Query("SELECT d FROM Manager d WHERE d.isDeleted = false")
    List<Manager> findAll();
    List<Manager> findBySalaryGreaterThan(int salary);
}
