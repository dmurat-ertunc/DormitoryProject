package com.dme.DormitoryProject.repository;

import com.dme.DormitoryProject.entity.Department;
import com.dme.DormitoryProject.entity.Lgo;
import com.dme.DormitoryProject.entity.Student;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILgoDao extends JpaRepository<Lgo,Long> {
    Lgo findLgoById(Long id);
    //@Query("select * from log_tbl where is_deleted = false")
    //List<Log> getAll();
    @Query("SELECT d FROM Lgo d WHERE d.isDeleted = false")
    List<Lgo> findAll();

}
