package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.apiController.UniversityController;
import com.dme.DormitoryProject.entity.University;

import java.util.List;
import java.util.Optional;

public interface IUniversityService {
    List<University> getAll();
    Optional<University> getById(Long id);
    University saveUniversity(University university);
    University updateUniversity(Long id, University university);
    University deleteUniversity(Long id);

}
