package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Student;
import com.dme.DormitoryProject.entity.Title;
import com.dme.DormitoryProject.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUniversityDao extends JpaRepository<University,Long> {
    @Query("SELECT d FROM University d WHERE d.isDeleted = false")
    List<University> findAll();

}
