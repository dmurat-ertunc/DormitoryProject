package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStaffDao extends JpaRepository<Staff,Long> {
    @Query("SELECT d FROM Staff d WHERE d.isDeleted = false")
    List<Staff> findAll();

}
