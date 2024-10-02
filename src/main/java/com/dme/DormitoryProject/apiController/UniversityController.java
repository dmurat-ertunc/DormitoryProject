package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IUniversityService;
import com.dme.DormitoryProject.dtos.universityDtos.UniversityDTO;
import com.dme.DormitoryProject.entity.University;
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
    public List<UniversityDTO> getAll(){
        return this.universityService.getAll();
    }
    @GetMapping("universityId/{id}")
    public Optional<UniversityDTO> getById(@PathVariable Long id){
        return this.universityService.getById(id);
    }
    @PostMapping("saveUniversity")
    public University saveUniversity(@RequestBody UniversityDTO universityDTO) {
        return this.universityService.saveUniversity(universityDTO);
    }
    @PutMapping("update/{id}")
    public University updateUniversity(@PathVariable Long id, @RequestBody UniversityDTO universityDTO) {
        return this.universityService.updateUniversity(id,universityDTO);
    }
    @PutMapping("delete/{id}")
    public University deleteUniversity(@PathVariable Long id){
        return this.universityService.deleteUniversity(id);
    }

}
