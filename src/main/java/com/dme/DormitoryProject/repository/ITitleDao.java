package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITitleDao extends JpaRepository<Title,Long> {
    @Query("SELECT d FROM Title d WHERE d.isDeleted = false")
    List<Title> findAll();
}
