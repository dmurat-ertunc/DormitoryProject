package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.LogLevel;
import com.dme.DormitoryProject.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILogLevelDao extends JpaRepository<LogLevel,Long> {
    //LogLevel findLogLevelById(Long id);
    @Query("SELECT d FROM LogLevel d WHERE d.isDeleted = false")
    List<LogLevel> findAll();
}
