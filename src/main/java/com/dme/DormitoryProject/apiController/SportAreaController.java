package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ISporAreaService;
import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
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
    public List<SportAreaDTO> getAll(){
        return this.sporAreaService.getAll();
    }

    @GetMapping("sportAreaId/{id}")
    public Optional<SportAreaDTO> getbyId(@PathVariable Long id){
        return this.sporAreaService.getById(id);
    }

    @PostMapping("saveSportArea")
    public SportArea saveSportArea(@RequestBody SportAreaDTO sportAreaDTO){
        return this.sporAreaService.saveSportArea(sportAreaDTO);
    }
    @PutMapping("update/{id}")
    public SportArea updateSportArea(@PathVariable Long id, @RequestBody SportAreaDTO sportAreaDTO){
        return this.sporAreaService.updateSportArea(id,sportAreaDTO);
    }
    @PutMapping("delete/{id}")
    public SportArea deleteSporArea(@PathVariable Long id){
        return this.sporAreaService.deleteSportArea(id);
    }
}
