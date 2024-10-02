package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.apiController.UniversityController;
import com.dme.DormitoryProject.dtos.universityDtos.UniversityDTO;
import com.dme.DormitoryProject.entity.University;

import java.util.List;
import java.util.Optional;

public interface IUniversityService {
    List<UniversityDTO> getAll();
    Optional<UniversityDTO> getById(Long id);
    University saveUniversity(UniversityDTO universityDTO);
    University updateUniversity(Long id, UniversityDTO universityDTO);
    University deleteUniversity(Long id);

}
