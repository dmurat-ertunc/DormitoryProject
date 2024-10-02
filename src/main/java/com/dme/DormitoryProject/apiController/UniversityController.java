package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.IUniversityService;
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
    public List<University> getAll(){
        return this.universityService.getAll();
    }
    @GetMapping("universityId/{id}")
    public Optional<University> getById(@PathVariable Long id){
        return this.universityService.getById(id);
    }
    @PostMapping("saveUniversity")
    public University saveUniversity(@RequestBody University university) {
        return this.universityService.saveUniversity(university);
    }
    @PutMapping("update/{id}")
    public University updateUniversity(@PathVariable Long id, @RequestBody University university) {
        return this.universityService.updateUniversity(id,university);
    }
    @PutMapping("delete/{id}")
    public University deleteUniversity(@PathVariable Long id){
        return this.universityService.deleteUniversity(id);
    }

}
