package com.dme.DormitoryProject.Manager.Abstract;

import com.dme.DormitoryProject.apiController.UniversityController;
import com.dme.DormitoryProject.dtos.universityDtos.UniversityDTO;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.response.Result;

import java.util.List;
import java.util.Optional;

public interface IUniversityService {
    Result getAll();
    Result getById(Long id);
    Result saveUniversity(UniversityDTO universityDTO);
    Result updateUniversity(Long id, UniversityDTO universityDTO);
    Result deleteUniversity(Long id);

}
