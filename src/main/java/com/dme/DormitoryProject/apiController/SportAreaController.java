package com.dme.DormitoryProject.apiController;

import com.dme.DormitoryProject.Manager.Abstract.ISporAreaService;
import com.dme.DormitoryProject.dtos.sportAreaDtos.SportAreaDTO;
import com.dme.DormitoryProject.entity.SportArea;
import com.dme.DormitoryProject.response.Result;
import jakarta.validation.Valid;
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
    public Result getAll(){
        return this.sporAreaService.getAll();
    }

    @GetMapping("sportAreaId/{id}")
    public Result getbyId(@PathVariable Long id){
        return this.sporAreaService.getById(id);
    }

    @PostMapping("saveSportArea")
    public Result saveSportArea(@RequestBody @Valid SportAreaDTO sportAreaDTO){
        return this.sporAreaService.saveSportArea(sportAreaDTO);
    }
    @PutMapping("update/{id}")
    public Result updateSportArea(@PathVariable Long id, @RequestBody @Valid SportAreaDTO sportAreaDTO){
        return this.sporAreaService.updateSportArea(id,sportAreaDTO);
    }
    @PutMapping("delete/{id}")
    public Result deleteSporArea(@PathVariable Long id){
        return this.sporAreaService.deleteSportArea(id);
    }
}
