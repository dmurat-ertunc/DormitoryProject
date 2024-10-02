package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ISporAreaService;
import com.dme.DormitoryProject.entity.SportArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/sportAreas/")
public class SportAreaController {

    private ISporAreaService sporAreaService;
    @Autowired
    public SportAreaController(ISporAreaService sporAreaService){
        this.sporAreaService=sporAreaService;
    }

    @GetMapping("getAll")
    public List<SportArea> getAll(){
        return this.sporAreaService.getAll();
    }

    @GetMapping("sportAreaId/{id}")
    public Optional<SportArea> getbyId(@PathVariable Long id){
        return this.sporAreaService.getById(id);
    }

    @PostMapping("saveSportArea")
    public SportArea saveSportArea(@RequestBody SportArea sportArea){
        return this.sporAreaService.saveSportArea(sportArea);
    }
    @PutMapping("update/{id}")
    public SportArea updateSportArea(@PathVariable Long id, @RequestBody SportArea sportArea){
        return this.sporAreaService.updateSportArea(id,sportArea);
    }
    @PutMapping("delete/{id}")
    public SportArea deleteSporArea(@PathVariable Long id){
        return this.sporAreaService.deleteSportArea(id);
    }
}
