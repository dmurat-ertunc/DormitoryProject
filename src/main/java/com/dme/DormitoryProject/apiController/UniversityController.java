package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IUniversityService;
import com.dme.DormitoryProject.dtos.universityDtos.UniversityDTO;
import com.dme.DormitoryProject.entity.University;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/universities/")
public class UniversityController {
    private IUniversityService universityService;
    @Autowired
    public UniversityController(IUniversityService universityService){
        this.universityService=universityService;
    }
    @GetMapping("getAll")
    public Result getAll(){
        return this.universityService.getAll();
    }
    @GetMapping("universityId/{id}")
    public Result getById(@PathVariable Long id){
        return this.universityService.getById(id);
    }
    @PostMapping("saveUniversity")
    public Result saveUniversity(@RequestBody @Valid UniversityDTO universityDTO) {
        return this.universityService.saveUniversity(universityDTO);
    }
    @PutMapping("update/{id}")
    public Result updateUniversity(@PathVariable Long id, @RequestBody @Valid UniversityDTO universityDTO) {
        return this.universityService.updateUniversity(id,universityDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteUniversity(@PathVariable Long id){
        return this.universityService.deleteUniversity(id);
    }

}
