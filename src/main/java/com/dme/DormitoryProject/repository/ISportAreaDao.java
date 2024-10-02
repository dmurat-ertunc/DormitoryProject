package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISportAreaDao extends JpaRepository<SportArea,Long> {
    @Query("SELECT d FROM SportArea d WHERE d.isDeleted = false")
    List<SportArea> findAll();
}
